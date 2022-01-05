package com.iiitlucknow.android.festify

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.iiitlucknow.android.festify.ViewModels.api_view_model
import com.iiitlucknow.android.festify.data_classes.my_post
import com.iiitlucknow.android.festify.databinding.FragmentSignUpBinding
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import java.io.ByteArrayOutputStream


class SignUpFragment : Fragment() {
    private var my_data: Uri? = null
    private val IMAGE_PICK_CODE = 1000
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: api_view_model
    lateinit var bitmap: Bitmap
    lateinit var encodedImage: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(api_view_model::class.java)
        viewModel.myResponse.observe(viewLifecycleOwner, {
            if (it.isSuccessful) {
                Toast.makeText(context, "Win", Toast.LENGTH_LONG).show()
                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra("p_img", my_data.toString())
                intent.putExtra("u_name", binding.setUsername.text.toString().trim())
                startActivity(intent)
            } else {
                Toast.makeText(context, "Try again", Toast.LENGTH_LONG).show()
            }
        })
        binding.selImg.setOnClickListener {
            pick_img_from_gallery()
        }
        binding.changeToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        binding.fSignUpBtn.setOnClickListener {
            if (binding.setName.text.toString().trim().isEmpty()) {
                binding.laySetName.error = "Name cannot be empty"
            } else {
                binding.laySetName.error = null
            }
            if (binding.setUsername.text.toString().trim().isEmpty()) {
                binding.laySetUsername.error = "UserName cannot be empty"
            } else {
                binding.laySetUsername.error = null
            }
            if (binding.setEmail.text.toString().trim().isEmpty()) {
                binding.laySetEmail.error = "E-Mail cannot be empty"
            } else {
                binding.laySetEmail.error = null
            }
            if (binding.setPassword.text.toString().trim().isEmpty()) {
                binding.laySetPassword.error = "Password cannot be empty"
            } else {
                binding.laySetPassword.error = null
            }
            if (binding.setConfirmPassword.text.toString().trim().isEmpty()) {
                binding.laySetConfirmPassword.error = "Confirm Password cannot be empty"
            } else {
                binding.laySetConfirmPassword.error = null
            }
            if (binding.setConfirmPassword.text.toString().trim() != binding.setPassword.toString().trim()
            ) {
                binding.laySetConfirmPassword.error =
                    "Confirm Password is not the same as the Password"
            } else {
                binding.laySetConfirmPassword.error = null
            }
            if (binding.setConfirmPassword.text.toString().trim().isEmpty()) {
                binding.laySetConfirmPassword.error = "Confirm Password cannot be empty"
            }


            if (binding.setName.text.toString().trim().isNotEmpty() &&
                binding.setUsername.text.toString().trim().isNotEmpty() &&
                binding.setEmail.text.toString().trim().isNotEmpty() &&
                binding.setPassword.text.toString().trim().isNotEmpty() &&
                binding.setConfirmPassword.text.toString().trim().isNotEmpty() &&
                my_data != null
            ) {
                uploadImage()
                val my_post = my_post(
                    binding.setUsername.text.toString().trim(),
                    binding.setEmail.text.toString().trim(),
                    binding.setPassword.text.toString().trim(),
                    encodedImage
                )
                viewModel.pushPost(my_post)
            } else {

                Toast.makeText(
                    activity,
                    "Fill all the details",
                    Toast.LENGTH_LONG
                ).show()
            }


        }

        return binding.root
    }

    private fun uploadImage() {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream)
        val imageByte: ByteArray = byteArrayOutputStream.toByteArray()
        encodedImage = Base64.encodeToString(imageByte, Base64.DEFAULT)
        Toast.makeText(context, encodedImage, Toast.LENGTH_LONG).show()
    }

    private fun pick_img_from_gallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                IMAGE_PICK_CODE -> {
                    data?.data?.let {
                        crop_the_image(it)
                    }
                }
                CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                    val result = CropImage.getActivityResult(data)
                    bitmap = MediaStore.Images.Media.getBitmap(
                        requireContext().contentResolver,
                        result.uri
                    )
                    binding.selProfileImg.setImageBitmap(bitmap)
                    my_data = result.uri
                }
            }
        }
    }

    private fun crop_the_image(uri: Uri) {
        CropImage.activity(uri)
            .setGuidelines(CropImageView.Guidelines.ON)
            .setAspectRatio(1080, 1080)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(requireContext(), this)
    }

}