package com.iiitlucknow.android.festify.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iiitlucknow.android.festify.R
import com.iiitlucknow.android.festify.databinding.FragmentEventsBinding
import com.iiitlucknow.android.model.EventRecyclerItem
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations

class EventFragment : Fragment() {
    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!
    private lateinit var sliderAdapter: EventImageSliderAdapter
    private var images: MutableList<Int> = mutableListOf(
        R.drawable.android_img,
        R.drawable.web_development_img,
        R.drawable.cybersecurity_img,
        R.drawable.ai_img,
        R.drawable.designing_img
    )
    private var fields: MutableList<EventRecyclerItem> = mutableListOf(
        EventRecyclerItem(R.drawable.android, R.string.android),
        EventRecyclerItem(R.drawable.web_development, R.string.web),
        EventRecyclerItem(
            R.drawable.cyber_security,
            R.string.cyber_sec
        ),
        EventRecyclerItem(R.drawable.ai, R.string.ai),
        EventRecyclerItem(R.drawable.design, R.string.design)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventsBinding.inflate(
            inflater, container, false
        )
        sliderAdapter = EventImageSliderAdapter(images)
        binding.apply {
            slide.setSliderAdapter(sliderAdapter)
            slide.setIndicatorAnimation(IndicatorAnimationType.WORM)
            slide.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
            slide.startAutoCycle()
            recycler.adapter = EventRecyclerViewAdapter(fields)
            recycler.setHasFixedSize(true)
        }
        return (binding.root)
    }
}
