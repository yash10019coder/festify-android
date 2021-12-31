package com.iiitlucknow.android.festify

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.iiitlucknow.android.festify.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    private var my_data: String? = null
    private val IMAGE_PICK_CODE = 1000
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.selImg.setOnClickListener {
            pick_img_from_gallery()
        }
        binding.changeToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        binding.fSignUpBtn.setOnClickListener {
            if (binding.setUsername.text.toString().trim().isNotEmpty() && my_data != null) {
                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra("p_img", my_data)
                intent.putExtra("u_name", binding.setUsername.text.toString().trim())
                startActivity(intent)
            } else {
                Toast.makeText(activity, "Fill image and username", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    private fun pick_img_from_gallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            my_data = data?.dataString.toString()
            binding.selProfileImg.load(my_data)
        }
    }
}