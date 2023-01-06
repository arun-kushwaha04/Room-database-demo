package com.example.roomdbdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbdemo.db.WatchItem
import com.example.roomdbdemo.db.WatchListDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainActivityViewModel
    private lateinit var toWatchName : EditText
    private lateinit var toWatchGenera : EditText
    private lateinit var rvWatchList : RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var selectedItem : WatchItem
    private lateinit var addButton: Button
    private var isUpdateOccurring = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toWatchName = findViewById<EditText>(R.id.etName)
        toWatchGenera = findViewById<EditText>(R.id.etGenera)
        rvWatchList = findViewById<RecyclerView>(R.id.rvWatchlist)
        addButton = findViewById<Button>(R.id.btnAdd)
        val clearButton = findViewById<Button>(R.id.btnClear)

        val dao = WatchListDatabase.getInstance(this).WatchListDao()
        val factory = MainActivityViewModelFactory(dao);
        viewModel = ViewModelProvider(this,factory)[MainActivityViewModel::class.java]

        initRecyclerView()

        addButton.setOnClickListener{
            if(validateInput()){
                if(isUpdateOccurring)updateWatchItem(selectedItem)
                else addToWatchList()
                clearInput()
            }else{
                Toast.makeText(this,"All field are required", Toast.LENGTH_SHORT).show()
            }
        }

        clearButton.setOnClickListener {
            clearInput()
        }
    }

    private fun initRecyclerView() {
        rvWatchList.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter({ selectedItem: WatchItem ->
            deleteWatchItem(
                selectedItem
            )
        }, { selectedItem: WatchItem -> setTextViews(selectedItem) })
        rvWatchList.adapter = adapter

            setRecyclerViewData()
    }

    private fun setTextViews(selectedItem: WatchItem) {
        toWatchName.setText(selectedItem.name)
        toWatchGenera.setText(selectedItem.genera)
        isUpdateOccurring = true
        this.selectedItem = selectedItem
        addButton.text = "Update"
    }

    private fun setRecyclerViewData() {
        viewModel.watchList.observe(this) {
            adapter.updateList(it!!)
            adapter.notifyDataSetChanged()
        }
    }

    private fun validateInput() : Boolean{
        return toWatchName.text.toString().isNotEmpty() && toWatchGenera.text.toString()
            .isNotEmpty()
    }

    private fun addToWatchList(){
        viewModel.insertWatchItem(WatchItem(0,toWatchName.text.toString(),toWatchGenera.text.toString()))
    }

    private fun clearInput(){
        toWatchName.setText("")
        toWatchGenera.setText("")
        isUpdateOccurring = false
        addButton.text = "Add"
    }

    private fun updateWatchItem(watchItem : WatchItem) {
        viewModel.updateWatchItem(WatchItem(watchItem.id,toWatchName.text.toString(),toWatchGenera.text.toString()))
    }

    private fun deleteWatchItem(watchItem: WatchItem){
        viewModel.deleteWatchItem(WatchItem(watchItem.id,toWatchName.text.toString(),toWatchGenera.text.toString()))
    }
}