package com.iiitlucknow.android.festify

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.iiitlucknow.android.festify.ViewModels.api_view_model
import com.iiitlucknow.android.festify.data_classes.my_post
import com.iiitlucknow.android.festify.databinding.FragmentSignUpBinding
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import java.io.ByteArrayOutputStream
import org.json.JSONException
import org.json.JSONObject

class SignUpFragment : Fragment() {
    private var my_data: Uri? = null
    private val IMAGE_PICK_CODE = 1000
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: api_view_model
    lateinit var bitmap: Bitmap
    lateinit var encodedImage: String
    lateinit var s_msg: String
    lateinit var f_msg: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(api_view_model::class.java)
        viewModel.myResponse.observe(viewLifecycleOwner) {

            if (it.isSuccessful) {
                try {
                    val jsonObject = JSONObject(Gson().toJson(it.body()))
                    s_msg = jsonObject.getString("message")
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                Toast.makeText(context, s_msg, Toast.LENGTH_LONG).show()
                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra("p_img", my_data.toString())
                intent.putExtra("u_name", binding.setUsername.text.toString().trim())
                startActivity(intent)
            } else {
                try {
                    val jObjError = JSONObject(it.errorBody()!!.string())
                    f_msg = jObjError.getString("message");
                    if (f_msg.contains("username")) {
                        binding.laySetUsername!!.error = "Username is already taken"
                    }
                    if (f_msg.contains("email")) {
                        binding.laySetEmail!!.error = "E-Mail is already taken"
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }


            }
        }
        binding.setEmail.doOnTextChanged { text, start, before, count ->
            if (!email_check(text.toString())) {
                binding.laySetEmail!!.error = "Invalid E-Mail Format"
            } else {
                binding.laySetEmail!!.error = null
            }
        }
        binding.setName.doOnTextChanged { text, start, before, count ->
            if (text.toString().isNotEmpty()) {
                binding.laySetName!!.error = null
            }
        }
        binding.setUsername.doOnTextChanged { text, start, before, count ->
            if (text.toString().isNotEmpty()) {
                binding.laySetUsername!!.error = null
            }
        }
        binding.setPassword.doOnTextChanged { text, start, before, count ->
            if (text.toString().isNotEmpty()) {
                binding.laySetPassword!!.error = null
            }
        }
        binding.setConfirmPassword.doOnTextChanged { text, start, before, count ->
            if (text.toString().isNotEmpty()) {
                binding.laySetConfirmPassword!!.error = null
            }
        }
        binding.selImg.setOnClickListener {
            pick_img_from_gallery()
        }
        binding.changeToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        binding.fSignUpBtn.setOnClickListener {
            if (binding.setName.text.toString().trim().isEmpty()) {
                binding.laySetName!!.error = "Name cannot be empty"
            }
            if (binding.setUsername.text.toString().trim().isEmpty()) {
                binding.laySetUsername!!.error = "UserName cannot be empty"
            }
            if (binding.setEmail.text.toString().trim().isEmpty()) {
                binding.laySetEmail!!.error = "E-Mail cannot be empty"
            }
            if (binding.setPassword.text.toString().trim().isEmpty()) {
                binding.laySetPassword!!.error = "Password cannot be empty"
            }
            if (binding.setConfirmPassword.text.toString().trim().isEmpty()) {
                binding.laySetConfirmPassword!!.error = "Confirm Password cannot be empty"
            }
            if (binding.setConfirmPassword.text.toString()
                    .trim() != binding.setPassword.text.toString()
                    .trim()
            ) {
                binding.laySetConfirmPassword!!.error =
                    "Confirm Password is not the same as the Password"
            }



            if (checks()) {
                uploadImage()
                val my_post = my_post(
                    binding.setUsername.text.toString().trim(),
                    binding.setPassword.text.toString().trim(),
                    binding.setEmail.text.toString().trim(),
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

    private fun checks(): Boolean {
        if (binding.setName.text.toString().trim().isNotEmpty() &&
            binding.setUsername.text.toString().trim().isNotEmpty() &&
            binding.setEmail.text.toString().trim().isNotEmpty() &&
            binding.setPassword.text.toString().trim().isNotEmpty() &&
            binding.setConfirmPassword.text.toString().trim().isNotEmpty() &&
            my_data != null && email_check(binding.setEmail.text.toString().trim())
            && binding.setPassword.text.toString()
                .trim() == binding.setConfirmPassword.text.toString().trim()
        ) {
            return true
        }
        return false
    }

    private fun email_check(text: String): Boolean {
        if (Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            return true
        }
        return false
    }

    private fun uploadImage() {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream)
        val imageByte: ByteArray = byteArrayOutputStream.toByteArray()
        encodedImage = Base64.encodeToString(imageByte, Base64.DEFAULT)
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