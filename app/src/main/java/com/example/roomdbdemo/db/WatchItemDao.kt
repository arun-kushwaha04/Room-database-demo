package com.example.roomdbdemo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WatchItemDao {
    @Insert
    suspend fun insertWatchItem(watchItem : watchItem)

    @Delete
    suspend fun deleteWatchItem(watchItem: watchItem)

    @Update
    suspend fun updateWatchItem(watchItem: watchItem)

    @Query("SELECT * FROM watch_list")
    fun getWatchList():LiveData<List<watchItem>>
}