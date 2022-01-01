package com.iiitlucknow.android.festify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.iiitlucknow.android.festify.databinding.FragmentStartBinding


class start : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        binding.signupBtn.setOnClickListener {
            findNavController().navigate(R.id.action_start2_to_signUpFragment)
        }
        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_start2_to_loginFragment)
        }



        return binding.root

    }


}