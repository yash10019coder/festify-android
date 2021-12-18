package com.iiitlucknow.festify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iiitlucknow.festify.databinding.FragmentEventsBinding
import com.iiitlucknow.festify.databinding.FragmentTeamsBinding

class TeamsFragment : Fragment() {
    private var _binding: FragmentTeamsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamsBinding.inflate(inflater, container, false)

        return binding.root
    }
}
