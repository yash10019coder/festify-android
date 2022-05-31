package com.iiitlucknow.android.festify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.iiitlucknow.android.festify.Adapters.recyclerAdapter
import com.iiitlucknow.android.festify.Adapters.sliderAdapter
import com.iiitlucknow.android.festify.ViewModels.MainViewModel
import com.iiitlucknow.android.festify.data_classes.add_event_data
import com.iiitlucknow.android.festify.data_classes.recyclerItem
import com.iiitlucknow.android.festify.databinding.FragmentEventsBinding
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations

class EventsFragment : Fragment() {
    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!
    private lateinit var sliderAdapter: sliderAdapter
    lateinit var vm:MainViewModel
    private var images: MutableList<Int> = mutableListOf(
        R.drawable.android_img,
        R.drawable.web_development_img,
        R.drawable.cybersecurity_img,
        R.drawable.ai_img,
        R.drawable.designing_img
    )
    private var fields: MutableList<recyclerItem> = mutableListOf(
        recyclerItem(R.drawable.android, R.string.android),
        recyclerItem(R.drawable.web_development, R.string.web),
        recyclerItem(R.drawable.cyber_security, R.string.cyber_sec),
        recyclerItem(R.drawable.ai, R.string.ai),
        recyclerItem(R.drawable.design, R.string.design)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventsBinding.inflate(
            inflater, container, false
        )
        vm = ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(MainViewModel::class.java)

        vm.myname.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(),it.message[0].toString(), Toast.LENGTH_LONG).show()
           //change message[0] to diff values like message[1],message[2].. to get the values of other events
            // put the above data in a mutable list and segregate.
        }

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
