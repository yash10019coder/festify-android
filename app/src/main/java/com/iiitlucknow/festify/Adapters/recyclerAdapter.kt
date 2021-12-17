package com.iiitlucknow.festify.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iiitlucknow.festify.R
import com.iiitlucknow.festify.data.recyclerItem

class recyclerAdapter(private var list: MutableList<recyclerItem>) :
    RecyclerView.Adapter<recyclerAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recycler_text: TextView = view.findViewById(R.id.recycler_text)
        val recycler_icon: ImageView = view.findViewById(R.id.recycler_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recycler_icon.setImageResource(list[position].icon)
        holder.recycler_text.text = list[position].title

    }

    override fun getItemCount(): Int {
        return list.size
    }
}