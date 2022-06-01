package com.iiitlucknow.android.festify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.iiitlucknow.android.festify.Adapters.clickAdapter
import com.iiitlucknow.android.festify.ViewModels.MainViewModel
import com.iiitlucknow.android.festify.data_classes.add_event_data
import com.iiitlucknow.android.festify.data_classes.recyclerItemClick
import com.iiitlucknow.android.festify.databinding.FragmentClickBinding

class ClickFragment : Fragment() {
    private var _binding: FragmentClickBinding? = null
    private val binding get() = _binding!!
    val args: ClickFragmentArgs by navArgs()
    lateinit var adapter: clickAdapter
    lateinit var viewModel: MainViewModel
    var listOfEvents = mutableListOf<recyclerItemClick>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClickBinding.inflate(
            inflater, container, false
        )

        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(MainViewModel::class.java)

        viewModel.myname.observe(viewLifecycleOwner) {
            populateEventsList(it.message, args.event)
            binding.clickRecycler.adapter = clickAdapter(listOfEvents)
            binding.clickRecycler.setHasFixedSize(true)
        }
        return binding.root
    }

    private fun populateEventsList(items: MutableList<add_event_data>, category: String) {
        listOfEvents.clear()
        for (ele in items) {
            if (ele.eventCategory == category) {
                val dateTime = dateTimeFormat(
                    ele.eventDate,
                    ele.eventStartTime,
                    ele.eventEndDate,
                    ele.eventEndTime
                )
                val recycleritemclick = recyclerItemClick(
                    R.drawable.android_img,
                    ele.eventName,
                    dateTime,
                    ele.eventDescription,
                    ele.eventCategory
                )
                listOfEvents.add(recycleritemclick)
            }
        }
    }

    private fun dateTimeFormat(
        startDate: String,
        startTime: String,
        endDate: String,
        endTime: String
    ): String {
        val dateTime = StringBuilder(startDate).append(" ").append(startTime).append(" - ")
            .append(endDate).append(" ").append(endTime)
        return dateTime.toString()
    }

}
