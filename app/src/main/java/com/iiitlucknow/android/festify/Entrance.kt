package com.iiitlucknow.android.festify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Entrance : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)
        supportActionBar?.hide()
    }
}
