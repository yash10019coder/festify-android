package com.iiitlucknow.android.festify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iiitlucknow.android.festify.Adapters.recyclerAdapter
import com.iiitlucknow.android.festify.Adapters.sliderAdapter
import com.iiitlucknow.android.festify.data_classes.RecyclerItem
import com.iiitlucknow.android.festify.databinding.FragmentEventsBinding
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations

class EventsFragment : Fragment() {
    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!
    private lateinit var sliderAdapter: sliderAdapter
    private var images: MutableList<Int> = mutableListOf(
        R.drawable.android_img,
        R.drawable.web_development_img,
        R.drawable.cybersecurity_img,
        R.drawable.ai_img,
        R.drawable.designing_img
    )
    private var fields: MutableList<RecyclerItem> = mutableListOf(
        RecyclerItem(R.drawable.android, R.string.appdev),
        RecyclerItem(R.drawable.web_development, R.string.webdev),
        RecyclerItem(R.drawable.cyber_security, R.string.info_sec),
        RecyclerItem(R.drawable.ai, R.string.ml),
        RecyclerItem(R.drawable.design, R.string.designing),
        RecyclerItem(R.drawable.design, R.string.cp) ,
        RecyclerItem(R.drawable.design, R.string.foss),
        RecyclerItem(R.drawable.design, R.string.misc)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventsBinding.inflate(
            inflater, container, false
        )


        sliderAdapter = sliderAdapter(images)
        binding.apply {
            slide.setSliderAdapter(sliderAdapter)
            slide.setIndicatorAnimation(IndicatorAnimationType.WORM)
            slide.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
            slide.startAutoCycle()
            recycler.adapter = recyclerAdapter(fields)
            recycler.setHasFixedSize(true)
        }
        return (binding.root)
    }
}
