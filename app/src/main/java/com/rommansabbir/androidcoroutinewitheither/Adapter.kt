package com.rommansabbir.androidcoroutinewitheither

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class Adapter constructor(var context: Context, var data: MutableList<DataModel>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(position: Int) {
            view.name.text = data[position].laptopName
            view.model.text = data[position].model
            view.price.text = data[position].price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position)
    }
}