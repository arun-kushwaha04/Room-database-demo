package com.example.roomdbdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [WatchItem::class],
    version = 1,
    exportSchema = false
)
abstract class WatchListDatabase : RoomDatabase() {

    abstract fun WatchListDao() : WatchListDao

    companion object {
        //volatile property will make any write to the INSTANCE variable will be
        //visible to other threads immediately
        @Volatile
        private var INSTANCE : WatchListDatabase? = null

        fun getInstance(context : Context) : WatchListDatabase{
            if(INSTANCE == null){
                //synchronized function will lock the thread passed to it
                synchronized(this){
                    INSTANCE = buildDatabase(context);
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context : Context) : WatchListDatabase{
            return Room.databaseBuilder(
                    context.applicationContext,
                    WatchListDatabase::class.java,
                "watch_list"
                    ).build()
        }
    }
}
