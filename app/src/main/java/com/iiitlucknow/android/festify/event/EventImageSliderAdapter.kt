package com.iiitlucknow.android.festify.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.iiitlucknow.android.festify.R
import com.smarteist.autoimageslider.SliderViewAdapter

class EventImageSliderAdapter(private var images: MutableList<Int>) :
    SliderViewAdapter<EventImageSliderAdapter.Holder>() {
    class Holder(itemView: View) : ViewHolder(itemView) {
        var slider_img: ImageView = itemView.findViewById(R.id.slider_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.slider_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(viewHolder: Holder, position: Int) {
        viewHolder.slider_img.setImageResource(images[position])
    }

    override fun getCount(): Int {
        return images.size
    }
}
