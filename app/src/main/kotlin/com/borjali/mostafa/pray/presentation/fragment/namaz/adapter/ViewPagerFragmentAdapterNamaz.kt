package com.borjali.mostafa.pray.presentation.fragment.namaz.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.borjali.mostafa.pray.presentation.fragment.namaz.mostahabi.NamazMostahabiFragment
import com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb.NamazVajebFragment


open class ViewPagerFragmentAdapterNamaz(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return NamazVajebFragment()
            1 -> return NamazMostahabiFragment()
        }
        return NamazVajebFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}