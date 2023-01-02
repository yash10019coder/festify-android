package com.iiitlucknow.android.festify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.cloudinary.android.MediaManager
import java.util.HashMap

class Entrance : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)
        supportActionBar?.hide()
        config()
    }
    private fun config() {
        val appConfig: HashMap<String, String> = HashMap<String, String> ()
        appConfig["cloud_name"] = "dezs1agpn"
        appConfig["api_key"]="657298376448555"
        appConfig["api_secret"] = "MAmfrZubbbdkCQoNiLFB6vGNtCo"
        MediaManager.init(this, appConfig)
    }
}
