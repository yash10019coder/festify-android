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
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.iiitlucknow.android.festify.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import de.hdodenhof.circleimageview.CircleImageView

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.open, R.string.close)
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val view: View = binding.nav.getHeaderView(0)
        val profile_img: CircleImageView = view.findViewById(R.id.profile_img)
        val profile_username: TextView = view.findViewById(R.id.profile_username)
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmenthost
        ) as NavHostFragment
        binding.bottomNavigationBar.setupWithNavController(navHostFragment.navController)
        val extras = intent.extras
        if (extras != null) {
            profile_img.load(extras.getString("p_img"))
            profile_username.text = extras.getString("u_name")
        }
        binding.nav.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.contribute -> {
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://github.com/yash10019coder/festify-android")
                    startActivity(openURL)
                }
                R.id.requestverification -> {
                    /**
                     * Using this temporary message to transfer
                     */
                    val message = extras?.getString("u_name")
                    val intent = Intent(this,RequestVerificationActivity::class.java)
                        .apply { putExtra("u_name",message) }
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
