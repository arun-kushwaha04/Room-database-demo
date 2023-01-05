package com.example.roomdbdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roomdbdemo.db.WatchItem
import com.example.roomdbdemo.db.WatchListDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainActivityViewModel
    private lateinit var toWatchName : EditText
    private lateinit var toWatchGenera : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toWatchName = findViewById<EditText>(R.id.etName)
        toWatchGenera = findViewById<EditText>(R.id.etGenera)

        val dao = WatchListDatabase.getInstance(this).WatchListDao()
        val factory = MainActivityViewModelFactory(dao);
        viewModel = ViewModelProvider(this,factory)[MainActivityViewModel::class.java]


    }

    fun addToWatchList(){
        viewModel.insertWatchItem(WatchItem(0,toWatchName.text.toString(),toWatchGenera.text.toString()))
    }

    fun clearInput(){
        toWatchName.setText("")
        toWatchGenera.setText("")
    }

    fun updateWatchItem(watchItem : WatchItem) {
        viewModel.updateWatchItem(WatchItem(watchItem.id,toWatchName.text.toString(),toWatchGenera.text.toString()))
    }

    fun deleteWatchItem(watchItem: WatchItem){
        viewModel.deleteWatchItem(WatchItem(watchItem.id,toWatchName.text.toString(),toWatchGenera.text.toString()))
    }
}