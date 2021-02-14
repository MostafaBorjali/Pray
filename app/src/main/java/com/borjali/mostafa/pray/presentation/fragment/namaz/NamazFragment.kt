package com.borjali.mostafa.pray.presentation.fragment.namaz

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentNamazBinding
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.namaz.adapter.ViewPagerFragmentAdapterNamaz
import com.borjali.mostafa.pray.utils.HorizontalFlipTransformation
import com.borjali.mostafa.pray.utils.myket.MyketSupportHelper
import com.borjali.mostafa.pray.utils.myket.MyketSupportHelper.CheckAppUpdateListener
import com.borjali.mostafa.pray.utils.myket.MyketSupportHelper.OnMyketSetupFinishedListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class NamazFragment : BaseFragment<FragmentNamazBinding>() {
    private var mMyketHelper: MyketSupportHelper? = null
    private val titles = listOf("نمازهای واجب ", "نماز های مستحبی")

    override fun getLayoutResourceId() = R.layout.fragment_namaz

    override fun oncreate() {
        menuHandel()
        initTabLayout()
        // myketUpdateService()
    }

    private fun initTabLayout() {
        binding.viewPager.adapter = activity?.let { ViewPagerFragmentAdapterNamaz(it) }
        binding.viewPager.setPageTransformer(HorizontalFlipTransformation())
        TabLayoutMediator(binding.tabLayout, binding.viewPager)
        { tab: TabLayout.Tab, position: Int ->
            tab.text = titles[position]
        }.attach()
        //binding.viewPager.currentItem = 1
        binding.viewPager.isSaveEnabled = false

    }

    private fun myketUpdateService() {

        mMyketHelper = MyketSupportHelper(activity)
        // Enable debug logging (for a production application, you should set this to false).
        // Enable debug logging (for a production application, you should set this to false).
        with(mMyketHelper) {
            this?.enableDebugLogging(true)

            // Start setup. This is asynchronous and the specified listener will be called once setup completes.

            // Start setup. This is asynchronous and the specified listener will be called once setup completes.


            this?.startSetup(OnMyketSetupFinishedListener { result ->
                Log.d("TAG", "Setup finished.")
                if (!result.isSuccess) {
                    // Oh noes, there was a problem or isDeveloperApiSupport is equal false.
                    // We suggest the app becomes closed if Myket is not installed or its too old.

                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                    return@OnMyketSetupFinishedListener
                }
                getAppUpdateStateAsync(mCheckAppUpdateListener)
            })
        }
    }

    private val mCheckAppUpdateListener =
        CheckAppUpdateListener { result, update ->

            if (!result.isSuccess) {
                Toast.makeText(activity, "Error Conenction ", Toast.LENGTH_SHORT).show()
                return@CheckAppUpdateListener
            }
            if (update.isUpdateAvailable) {
                Toast.makeText(activity, "you needed update", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "has latest version", Toast.LENGTH_SHORT).show()
            }
        }

    private fun menuHandel() {
        with(binding.menuNamaz) {
            btnMenuBack.visibility = View.GONE
            btnInfo.visibility = View.GONE
            txtTitleMenu.text = getString(R.string.namaz)
            btnInfo.setOnClickListener { findNavController().navigate(R.id.aboutFragment2) }
        }
    }
}
