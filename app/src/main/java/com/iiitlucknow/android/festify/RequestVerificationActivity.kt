package com.iiitlucknow.android.festify

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.iiitlucknow.android.festify.viewModels.RequestVerificationViewModel
import com.iiitlucknow.android.festify.data_classes.VerifyData
import com.iiitlucknow.android.festify.databinding.ActivityRequestVerificationBinding
import org.json.JSONException
import org.json.JSONObject

class RequestVerificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRequestVerificationBinding
    private lateinit var viewModel: RequestVerificationViewModel
    private lateinit var sMsg: String
    private lateinit var fMsg: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestVerificationBinding.inflate(layoutInflater)

        val view = binding.root
        viewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(RequestVerificationViewModel::class.java)
        viewModel.verifyResponse.observe(
            this
        ) {
            if (it.isSuccessful) {
                try {
                    val jsonObject = JSONObject(Gson().toJson(it.body()))
                    sMsg = jsonObject.getString("message")
                } catch (e: JSONException) {
                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                }
                Toast.makeText(this, sMsg, Toast.LENGTH_LONG).show()
            }
         else {
            try {
                val jObjError = JSONObject(it.errorBody()!!.string())
                fMsg = jObjError.getString("message")
                Toast.makeText(this, fMsg, Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
        supportActionBar?.title = "Request Verification"
        val userName = intent.getStringExtra("username")
        val userEmail = intent.getStringExtra("email")
        binding.verificationUsernameEditText.setText(userName)
        binding.verificationEmailEditText.setText(userEmail)

        initSpinner(binding.verificationWingSpinner, R.array.wing_names)
        initSpinner(binding.verificationDesignationSpinner, R.array.designations)

        setContentView(view)

        binding.verificationNameEditText.doOnTextChanged { text, start, before, count ->
            if (text.toString().isNotEmpty()) {
                binding.verificationNameLayout.error = null
            }
        }

        binding.submitRequestBtn.setOnClickListener {
            if (binding.verificationNameEditText.text.toString().trim().isEmpty()) {
                binding.verificationNameLayout.error = "Name cannot be empty"
            }
            if(binding.verificationNameEditText.text.toString().trim().isNotEmpty()){
                val verifyData = VerifyData(
                    userName.toString(),
                    binding.verificationNameEditText.text.toString().trim(),
                    userEmail.toString(),
                    binding.verificationWingSpinner.selectedItem.toString(),
                    binding.verificationDesignationSpinner.selectedItem.toString()
                )
                viewModel.verify(verifyData)
            }
            else{
                Toast.makeText(
                    this,
                    "Fill all the details",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun initSpinner(_spinner: Spinner, _arrayId: Int) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            _arrayId,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            _spinner.adapter = adapter
        }
    }
}
