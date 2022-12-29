package com.iiitlucknow.android.festify.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.iiitlucknow.android.festify.MyDialogFragment
import com.iiitlucknow.android.festify.R
import com.iiitlucknow.android.festify.data_classes.EventDataMessageModel


class homeAdapter(
    private val list: MutableList<EventDataMessageModel>
) : RecyclerView.Adapter<homeAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recycler_clicker_title: TextView = view.findViewById(R.id.recycler_clicker_title)
        val recycler_clicker_image: ImageView = view.findViewById(R.id.recycler_clicker_image)
        val recycler_clicker_button: AppCompatButton = view.findViewById(R.id.btn)
        val date: TextView = view.findViewById(R.id.recycler_clicker_date)
        val recycler_clicker_desc: TextView = view.findViewById(R.id.recycler_clicker_desc)
        val recycler_clicker_category: TextView = view.findViewById(R.id.recycler_clicker_category)
        val context = view.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_clicker_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fm: FragmentManager = (holder.context as AppCompatActivity).supportFragmentManager
        val item = list[position]
        holder.recycler_clicker_title.text = item.eventName
        holder.date.text = item.eventDate
        holder.recycler_clicker_button.text = "UNREGISTER"
        holder.recycler_clicker_button.background =
            ContextCompat.getDrawable(holder.context, R.drawable.unregister_background)
        holder.recycler_clicker_button.setOnClickListener {
            val mydialog = MyDialogFragment(item._id, 0)
            mydialog.show(fm, "view")
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
