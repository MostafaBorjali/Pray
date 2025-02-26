package com.borjali.mostafa.pray.presentation.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import android.window.SplashScreen
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.amozeshFragment ||
                destination.id == R.id.namazFragment ||
                destination.id == R.id.aboutFragment
            ) {
                binding.bottomNavigationView.visibility = View.VISIBLE
            } else {
                binding.bottomNavigationView.visibility = View.GONE
            }
        }
        binding.bottomNavigationView.setupWithNavController(navController)

    }


    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.namazFragment ||
            navController.currentDestination?.id == R.id.amozeshFragment ||
            navController.currentDestination?.id == R.id.aboutFragment
        ) {
            if (doubleBackToExitPressedOnce) {
                finish()
            } else {
                Toast.makeText(this, getString(R.string.app_exit_message), Toast.LENGTH_SHORT)
                    .show()
            }
            this.doubleBackToExitPressedOnce = true

            Handler(Looper.getMainLooper()).postDelayed({
                doubleBackToExitPressedOnce = false
            }, 2000)

        } else {
            super.onBackPressed()
        }
    }

}
