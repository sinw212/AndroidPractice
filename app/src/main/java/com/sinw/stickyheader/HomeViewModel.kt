package com.sinw.stickyheader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModel: ViewModel() {

    private val _sampleList: MutableLiveData<List<ListItemData>> = MutableLiveData()
    val sampleList: LiveData<List<ListItemData>>
        get() = _sampleList

    fun initSampleList() {
        val sampleDataList = arrayListOf<SampleData>()
        for (i in 0..50) {
            sampleDataList.add(SampleData("sample[$i]"))
        }

        val itemList = arrayListOf<ListItemData>()
        itemList.add(ListItemData(SampleAdapter.TYPE_HEADER, ""))
        sampleDataList.forEachIndexed { index, sampleData ->
            if (index % 10 == 0) {
                itemList.add(ListItemData(SampleAdapter.TYPE_TOP_HOLDER, ""))
            }
            itemList.add(ListItemData(SampleAdapter.TYPE_ITEM, sampleData))
        }
        itemList.add(ListItemData(SampleAdapter.TYPE_BOTTOM, ""))

        _sampleList.value = itemList
    }
}

class HomeViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel() as T
        } else {
            throw IllegalArgumentException("Not Found ViewModel Class")
        }
    }
}