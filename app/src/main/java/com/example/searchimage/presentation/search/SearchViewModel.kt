package com.example.searchimage.presentation.search

import android.content.Context
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

    private val _searchList: MutableLiveData<List<SearchItem>> = MutableLiveData()
    val searchList: LiveData<List<SearchItem>> get() = _searchList

    fun searchKeywordInfo(query: String) = viewModelScope.launch {
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
        }.onFailure {
            Log.e("sinw", it.message.toString())
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
                date = document.datetime
            )
        }.orEmpty()

        fun createVideoItems(
            videos: SearchEntity<VideoDocumentEntity>
        ): List<SearchItem.VideoItem> = videos.documents?.map { document ->
            SearchItem.VideoItem(
                imgThumbnail = document.thumbnail,
                txtTitle = "[Video] ${document.title}",
                date = document.datetime
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
}

class SearchViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
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