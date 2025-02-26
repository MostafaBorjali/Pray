package com.borjali.mostafa.pray.presentation.fragment.namaz

import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentNamazBinding
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.namaz.adapter.ViewPagerFragmentAdapterNamaz
import com.borjali.mostafa.pray.utils.HorizontalFlipTransformation
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class NamazFragment : BaseFragment<FragmentNamazBinding>() {

    private val titles = listOf("نمازهای واجب ", "نماز های مستحبی")

    override fun getLayoutResourceId() = R.layout.fragment_namaz

    override fun oncreate() {
        initTabLayout()
    }

    private fun initTabLayout() {
        binding.viewPager.adapter = activity?.let { ViewPagerFragmentAdapterNamaz(it) }
        binding.viewPager.setPageTransformer(HorizontalFlipTransformation())
        TabLayoutMediator(binding.tabLayout, binding.viewPager)
        { tab: TabLayout.Tab, position: Int ->
            tab.text = titles[position]
        }.attach()

        binding.viewPager.isSaveEnabled = false

    }


}
