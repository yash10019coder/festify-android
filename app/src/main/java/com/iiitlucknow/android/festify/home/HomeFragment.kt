package com.iiitlucknow.android.festify.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.iiitlucknow.android.festify.Adapters.homeAdapter
import com.iiitlucknow.android.festify.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var homeFragmentPresenter: HomeFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLifecycleOwner
        return homeFragmentPresenter.handleOnCreateView(inflater, container, savedInstanceState)
    }
}
