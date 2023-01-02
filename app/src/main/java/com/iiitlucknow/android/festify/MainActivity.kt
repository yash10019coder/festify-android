package com.iiitlucknow.android.festify

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.iiitlucknow.android.festify.databinding.ActivityMainBinding
import com.iiitlucknow.android.festify.viewModels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import de.hdodenhof.circleimageview.CircleImageView

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var viewModel: UserViewModel
    var token: Boolean = false
    lateinit var userName: String
    lateinit var userEmail:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(UserViewModel::class.java)
        val view: View = binding.nav.getHeaderView(0)
        val profile_img: CircleImageView = view.findViewById(R.id.profile_img)
        val profile_username: TextView = view.findViewById(R.id.profile_username)
        viewModel.myUserData.observe(this) {
            Glide.with(this)
                .load(it.message.userPhoto)
                .into(profile_img)

            profile_username.text = it.message.userName
            token = it.message.token
            userName = it.message.userName
            userEmail=it.message.userEmail
        }
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.open, R.string.close)
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmenthost
        ) as NavHostFragment
        binding.bottomNavigationBar.setupWithNavController(navHostFragment.navController)
        binding.nav.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.contribute -> {
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://github.com/yash10019coder/festify-android")
                    startActivity(openURL)
                }
                R.id.requestverification -> {
                    val message = userName
                    val intent = Intent(this, RequestVerificationActivity::class.java)
                    intent.putExtra("username", userName)
                    intent.putExtra("email",userEmail)
                    startActivity(intent)

                }
            }
            false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    var time = 0L
    override fun onBackPressed() {
        if (time + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(
                this, "Press once again to exit",
                Toast.LENGTH_SHORT
            ).show()
            time = System.currentTimeMillis()
        }
    }
}
