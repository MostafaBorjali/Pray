package com.borjali.mostafa.pray.presentation.fragment.splash


import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentSplashBinding
import com.borjali.mostafa.pray.presentation.base.BaseFragment

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment :
    BaseFragment<FragmentSplashBinding>() {

    override fun getLayoutResourceId() = R.layout.fragment_splash
    override fun oncreate() {
        lifecycleScope.launch{
            delay(2000L)
            findNavController().navigate(R.id.namazFragment)
        }
    }

}