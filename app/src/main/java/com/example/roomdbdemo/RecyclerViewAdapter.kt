package com.example.roomdbdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.roomdbdemo.db.WatchItem

class RecyclerViewAdapter(private val clickHandler: (WatchItem)->Unit, private val cardClickHandler: (WatchItem) -> Unit): RecyclerView.Adapter<MyViewHolder>() {
    private val watchList:ArrayList<WatchItem> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater  = LayoutInflater.from(parent.context)
        val layout = layoutInflater.inflate(R.layout.watch_item, parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(watchList[position], clickHandler, cardClickHandler)
    }

    override fun getItemCount(): Int {
        return watchList.size
    }

    fun updateList(newList : List<WatchItem>){
        watchList.clear()
        watchList.addAll(newList)
    }
}

class MyViewHolder(private val view: View) : ViewHolder(view){
    fun bind(watchItem: WatchItem, clickHandler: (WatchItem)->Unit, cardClickHandler: (WatchItem) -> Unit ){
    val cvMain = view.findViewById<CardView>(R.id.cvMain)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvGenera = view.findViewById<TextView>(R.id.tvGenera)
        val btnDelete = view.findViewById<Button>(R.id.btnDelete)

        tvName.text = watchItem.name
        tvGenera.text = watchItem.genera

        cvMain.setOnClickListener{
            cardClickHandler(watchItem)
        }

        btnDelete.setOnClickListener {
            clickHandler(watchItem)
        }
    }
}