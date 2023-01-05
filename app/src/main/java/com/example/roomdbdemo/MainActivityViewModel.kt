package com.example.roomdbdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdbdemo.db.WatchItem
import com.example.roomdbdemo.db.WatchListDao
import kotlinx.coroutines.launch

class MainActivityViewModel(private val watchListDao: WatchListDao): ViewModel() {
    val watchList = watchListDao.getWatchList()

    fun insertWatchItem(watchItem: WatchItem) = viewModelScope.launch {
        watchListDao.insertWatchItem(watchItem)
    }

    fun updateWatchItem(watchItem: WatchItem) = viewModelScope.launch {
        watchListDao.updateWatchItem(watchItem)
    }

    fun deleteWatchItem(watchItem: WatchItem) = viewModelScope.launch {
        watchListDao.deleteWatchItem(watchItem)
    }

}