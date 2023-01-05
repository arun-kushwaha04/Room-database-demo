package com.example.roomdbdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdbdemo.db.WatchListDao

class MainActivityViewModelFactory(private val dao : WatchListDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}