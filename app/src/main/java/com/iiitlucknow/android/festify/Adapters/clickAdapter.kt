package com.iiitlucknow.android.festify.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.iiitlucknow.android.festify.MyDialogFragment
import com.iiitlucknow.android.festify.R
import com.iiitlucknow.android.festify.data.my_events
import com.iiitlucknow.android.festify.data_classes.recyclerItemClick

class clickAdapter(private var list: MutableList<recyclerItemClick>) :
    RecyclerView.Adapter<clickAdapter.ViewHolder>() {
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
        holder.recycler_clicker_title.text = item.my_title
        holder.recycler_clicker_image.setImageResource(item.my_img)
        holder.date.text = item.date
        holder.recycler_clicker_desc.text = item.desc
        holder.recycler_clicker_category.text = item.category
        holder.recycler_clicker_button.setOnClickListener {
            val mydialog = MyDialogFragment(my_events(0, item.my_img, item.my_title, item.date,item.desc,item.category), 0)
            mydialog.show(fm, "view")


//            Toast.makeText(
//                holder.context,
//                "Registered for the Event " + holder.recycler_clicker_text.text +
//                    " on " + holder.date.text,
//                Toast.LENGTH_LONG
//            ).show()



        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
