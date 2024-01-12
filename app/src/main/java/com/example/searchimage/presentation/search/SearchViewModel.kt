package com.example.searchimage.presentation.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.searchimage.data.repository.SearchRepositoryImpl
import com.example.searchimage.domain.model.ImageDocumentEntity
import com.example.searchimage.domain.model.SearchEntity
import com.example.searchimage.domain.model.VideoDocumentEntity
import com.example.searchimage.domain.usecase.GetSearchImageParams
import com.example.searchimage.domain.usecase.GetSearchImageUseCase
import com.example.searchimage.domain.usecase.GetSearchVideoParams
import com.example.searchimage.domain.usecase.GetSearchVideoUseCase
import com.example.searchimage.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchImage: GetSearchImageUseCase,
    private val searchVideo: GetSearchVideoUseCase
) : ViewModel() {
    companion object {
        const val TAG = "SearchViewModel"
    }

    private val _searchList: MutableLiveData<List<SearchItem>> = MutableLiveData()
    val searchList: LiveData<List<SearchItem>>
        get() = _searchList

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    /**
     * 키워드가 이미 입력되어있는 경우, 다른 키워드 입력 후 검색 아이콘 클릭 시 list 비우기
     */
    fun clearSearchList() {
        if(_searchList.value.isNullOrEmpty()) {
            return
        }
        _searchList.value = emptyList()
    }

    /**
     * API 통신하여 키워드 검색 결과 출력
     */
    fun searchKeywordInfo(query: String) = viewModelScope.launch {
        _isLoading.value = true
        runCatching {
            val items = createItems(
                images = searchImage(
                    GetSearchImageParams(query)
                ),
                videos = searchVideo(
                    GetSearchVideoParams(query)
                )
            )
            _searchList.postValue(items)
            _isLoading.value = false
        }.onFailure {
            Log.e(TAG, it.message.toString())
            _isLoading.value = false
        }
    }

    private fun createItems(
        images: SearchEntity<ImageDocumentEntity>,
        videos: SearchEntity<VideoDocumentEntity>
    ): List<SearchItem> {

        fun createImageItems(
            images: SearchEntity<ImageDocumentEntity>
        ): List<SearchItem.ImageItem> = images.documents?.map { document ->
            SearchItem.ImageItem(
                imgThumbnail = document.thumbnailUrl,
                txtTitle = "[Image] ${document.displaySitename}",
                date = document.datetime,
                false
            )
        }.orEmpty()

        fun createVideoItems(
            videos: SearchEntity<VideoDocumentEntity>
        ): List<SearchItem.VideoItem> = videos.documents?.map { document ->
            SearchItem.VideoItem(
                imgThumbnail = document.thumbnail,
                txtTitle = "[Video] ${document.title}",
                date = document.datetime,
                false
            )
        }.orEmpty()

        val items = arrayListOf<SearchItem>().apply {
            addAll(createImageItems(images))
            addAll(createVideoItems(videos))
        }

        items.sortByDescending {
            it.date
        }

        return items
    }

    /**
     * 북마크 아이콘 클릭 시, 북마크 상태 변경
     */
    fun updateSearchItem(item: SearchItem?) {
        if (item == null) {
            return
        }

        val currentList = searchList.value.orEmpty().toMutableList()

        val findPosition = currentList.indexOfFirst { it.imgThumbnail == item.imgThumbnail }
        if (findPosition >= 0) {
            currentList[findPosition] = when (item) {
                is SearchItem.ImageItem -> item.copy(isBookmark = item.isBookmark)
                is SearchItem.VideoItem -> item.copy(isBookmark = item.isBookmark)
            }
            _searchList.value = currentList
        }
    }
}

class SearchViewModelFactory() : ViewModelProvider.Factory {
    private val repository = SearchRepositoryImpl(
        RetrofitClient.search
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(
                GetSearchImageUseCase(repository),
                GetSearchVideoUseCase(repository),
            ) as T
        } else {
            throw IllegalArgumentException("Not Found ViewModel Class")
        }
    }
}