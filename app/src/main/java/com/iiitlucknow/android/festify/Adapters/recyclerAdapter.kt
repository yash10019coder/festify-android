package com.iiitlucknow.android.festify.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.iiitlucknow.android.festify.EventsFragmentDirections
import com.iiitlucknow.android.festify.R
import com.iiitlucknow.android.festify.data_classes.recyclerItem

class recyclerAdapter(private var list: MutableList<recyclerItem>) :
    RecyclerView.Adapter<recyclerAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recycler_text: TextView = view.findViewById(R.id.recycler_text)
        val recycler_icon: ImageView = view.findViewById(R.id.recycler_icon)
        val context = view.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recycler_icon.setImageResource(list[position].icon)
        holder.recycler_text.text = holder.context.resources.getString(list[position].title)
        holder.itemView.setOnClickListener {
            val action =
                EventsFragmentDirections.actionEventsFragmentToClickFragment(
                    holder.recycler_text.text.toString()
                )
            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
