package com.example.wishlisttoo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TrackerAdapter(private val wishlistitems: List<DisplayTracker>) : RecyclerView.Adapter<TrackerAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Name: TextView
        var calories: TextView

        init{
            Name = itemView.findViewById(R.id.ViewName)
            calories = itemView.findViewById(R.id.ViewNum)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.wishlistitem, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return wishlistitems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = wishlistitems.get(position)
        holder.Name.text = item.itemname
        holder.calories.text = item.itemcals
    }
}