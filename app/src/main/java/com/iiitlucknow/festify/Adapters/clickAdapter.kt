package com.iiitlucknow.festify.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.iiitlucknow.festify.R
import com.iiitlucknow.festify.data.recyclerItemClick

class clickAdapter(private var list: MutableList<recyclerItemClick>) :
    RecyclerView.Adapter<clickAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recycler_clicker_text: TextView = view.findViewById(R.id.recycler_clicker_text)
        val recycler_clicker_img: ImageView = view.findViewById(R.id.my_img)
        val recycler_clicker_button: AppCompatButton = view.findViewById(R.id.btn)
        val date: TextView = view.findViewById(R.id.date)
        val context = view.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_clicker_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recycler_clicker_text.text =
            holder.context.resources.getString(list[position].my_title)
        holder.recycler_clicker_img.setImageResource(list[position].my_img)
        holder.date.text = holder.context.resources.getString(list[position].date)
        holder.recycler_clicker_button.setOnClickListener {
            Toast.makeText(
                holder.context,
                "Registered for the Event " + holder.recycler_clicker_text.text + 
                    " on " + holder.date.text,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
    
