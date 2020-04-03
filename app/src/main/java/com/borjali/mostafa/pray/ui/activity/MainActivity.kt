package com.borjali.mostafa.pray.ui.activity

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.ActivityMainBinding
import com.borjali.mostafa.pray.extention.setupWithNavController
import com.borjali.mostafa.pray.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private var currentNavController: LiveData<NavController>? = null

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun oncreate(savedInstanceState: Bundle?) {
        if (savedInstanceState==null){
            setupBottomNavigationBar()
            binding.navView.selectedItemId = R.id.rakaat_shomar_navigation
        }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.amozesh_navigation,
            R.navigation.rakaat_shomar_navigation,
            R.navigation.namaz_navigation
        )
        val controller = binding.navView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )
        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller

    }

    override fun onBackPressed() {
        if (currentNavController?.value?.popBackStack() != true) {
            finish()
        }
    }

}
