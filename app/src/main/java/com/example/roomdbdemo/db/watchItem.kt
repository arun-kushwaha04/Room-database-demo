package com.example.roomdbdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watch_list")
data class watchItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "watch_item_id")
    var id: Int,

    @ColumnInfo(name = "watch_item_name")
    val name: String,

    @ColumnInfo(name = "watch_item_genera")
    val genera: String)
