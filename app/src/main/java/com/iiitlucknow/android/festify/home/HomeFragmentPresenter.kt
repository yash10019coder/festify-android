package com.iiitlucknow.android.festify.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.iiitlucknow.android.festify.Adapters.homeAdapter
import com.iiitlucknow.android.festify.databinding.FragmentHomeBinding
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class HomeFragmentPresenter @Inject constructor(
    private val homeViewModel: HomeViewModel,
    private val activity: AppCompatActivity,
    private val fragment: Fragment
) {
    lateinit var binding: FragmentHomeBinding

    fun handleOnCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel.allwords.observe(
            fragment.viewLifecycleOwner
        ) {
            binding.homeRecycler.adapter = homeAdapter(it)
        }

        binding.homeRecycler.setHasFixedSize(true)

        return binding.root
    }
}