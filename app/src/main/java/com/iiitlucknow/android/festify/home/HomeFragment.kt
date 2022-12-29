package com.iiitlucknow.android.festify.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.iiitlucknow.android.festify.Adapters.homeAdapter
import com.iiitlucknow.android.festify.data_classes.EventDataMessageModel
//import com.iiitlucknow.android.festify.Adapters.homeAdapter
import com.iiitlucknow.android.festify.databinding.FragmentHomeBinding
import com.iiitlucknow.android.festify.viewModels.AddEventFragmentViewModel
import com.iiitlucknow.android.festify.viewModels.MainViewModel
import com.iiitlucknow.android.festify.viewModels.UserViewModel
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class HomeFragment : Fragment() {
    lateinit var homeViewModel: HomeViewModel
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(HomeViewModel::class.java)
        mainViewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(MainViewModel::class.java)

        var eve:MutableList<String>
        var myList:MutableList<EventDataMessageModel> = mutableListOf()
        homeViewModel.myUserData.observe(
            viewLifecycleOwner
        ) {
           eve=it.message.registeredEvents
            mainViewModel.myevent.observe(viewLifecycleOwner){j->
                for (i in j.message){
                    if(eve.contains(i._id)){
                      myList.add(i)
                    }
                }
            }
            binding.homeRecycler.adapter=homeAdapter(myList)
        }


        binding.homeRecycler.setHasFixedSize(true)

        return binding.root
    }
}
