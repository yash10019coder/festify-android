package com.iiitlucknow.android.festify.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
//import com.iiitlucknow.android.festify.Adapters.homeAdapter
import com.iiitlucknow.android.festify.databinding.FragmentHomeBinding
import com.iiitlucknow.android.festify.viewModels.UserViewModel
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class HomeFragment : Fragment() {
    lateinit var homeViewModel: HomeViewModel
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(HomeViewModel::class.java)

//        homeViewModel.myUserData.observe(
//            viewLifecycleOwner
//        ) {
//            binding.homeRecycler.adapter = homeAdapter(it)
//        }
//
//        binding.homeRecycler.setHasFixedSize(true)

        return binding.root
    }
}
