package com.iiitlucknow.android.festify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.iiitlucknow.android.festify.databinding.ActivityRequestVerificationBinding

class RequestVerificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRequestVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestVerificationBinding.inflate(layoutInflater)
        val view = binding.root
        getSupportActionBar()?.setTitle("Request Verification")
        val username = intent.getStringExtra("username")
        binding.verificationUsernameEditText.setText(username)

        initSpinner(binding.verificationWingSpinner,R.array.wing_names)
        initSpinner(binding.verificationDesignationSpinner,R.array.designations)

        setContentView(view)
    }

    private fun initSpinner(_spinner: Spinner, _arrayId: Int){
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
