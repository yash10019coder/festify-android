package com.iiitlucknow.android.festify.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iiitlucknow.android.festify.Adapters.homeAdapter
import com.iiitlucknow.android.festify.databinding.FragmentHomeBinding
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel.allwords.observe(
            viewLifecycleOwner
        ) {
            binding.homeRecycler.adapter = homeAdapter(it)
        }

        binding.homeRecycler.setHasFixedSize(true)

        return binding.root
    }
}
