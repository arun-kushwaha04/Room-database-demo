package com.example.roomdbdemo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WatchListDao {
    @Insert
    suspend fun insertWatchItem(watchItem : WatchItem)

    @Delete
    suspend fun deleteWatchItem(watchItem: WatchItem)

    @Update
    suspend fun updateWatchItem(watchItem: WatchItem)

    @Query("SELECT * FROM watch_list")
    fun getWatchList():LiveData<List<WatchItem>>
}