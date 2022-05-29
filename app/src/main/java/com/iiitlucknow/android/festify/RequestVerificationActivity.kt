package com.iiitlucknow.android.festify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iiitlucknow.android.festify.databinding.ActivityRequestVerificationBinding

class RequestVerificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRequestVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestVerificationBinding.inflate(layoutInflater)
        val view = binding.root

        /**
         * Using this message from the first activity to transfer username
         * from the main activity.
         */
        val message = intent.getStringExtra("u_name")
        binding.demo.text = message
        setContentView(view)
    }
}