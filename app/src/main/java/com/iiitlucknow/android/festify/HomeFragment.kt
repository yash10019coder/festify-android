package com.iiitlucknow.android.festify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.iiitlucknow.android.festify.Adapters.homeAdapter
import com.iiitlucknow.android.festify.ViewModels.home_view_model
import com.iiitlucknow.android.festify.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var homeViewModel: home_view_model
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel= ViewModelProvider(this).get(home_view_model::class.java)
        homeViewModel.allwords.observe(viewLifecycleOwner,{
            binding.homeRecycler.adapter=homeAdapter(it)
        })

        binding.homeRecycler.setHasFixedSize(true)

        return binding.root
    }
}
