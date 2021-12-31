package com.iiitlucknow.android.festify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Entrance : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)
        supportActionBar?.hide()
    }
}