package com.iiitlucknow.android.festify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.iiitlucknow.android.festify.Adapters.clickAdapter
import com.iiitlucknow.android.festify.viewModels.MainViewModel
import com.iiitlucknow.android.festify.data_classes.EventDataMessageModel
import com.iiitlucknow.android.festify.databinding.FragmentClickBinding

class ClickFragment : Fragment() {
    private var _binding: FragmentClickBinding? = null
    private val binding get() = _binding!!
    private val args: ClickFragmentArgs by navArgs()
    lateinit var viewModel: MainViewModel
    var listOfEvents = mutableListOf<EventDataMessageModel>()

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

        viewModel.myevent.observe(viewLifecycleOwner) {
            populateEventsList(it.message, args.event)
            binding.clickRecycler.adapter = clickAdapter(listOfEvents)
            binding.clickRecycler.setHasFixedSize(true)
        }
        return binding.root
    }

    private fun populateEventsList(items: MutableList<EventDataMessageModel>, category: String) {
        listOfEvents.clear()
        for (ele in items) {
            if (ele.eventCategory == category) {
                listOfEvents.add(ele)
            }
        }
    }

}
