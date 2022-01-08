package com.iiitlucknow.android.festify.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iiitlucknow.android.festify.Adapters.homeAdapter
import com.iiitlucknow.android.festify.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var homeViewModel: HomeViewModel

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
