package com.borjali.mostafa.pray.ui.fragment.namaz

import android.view.View
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentNamazBinding
import com.borjali.mostafa.pray.ui.base.BaseFragment

class NamazFragment : BaseFragment<FragmentNamazBinding>() {
    override fun getLayoutResourceId() = R.layout.fragment_namaz

    override fun oncreate() {
        menuHandel()


    }

    private fun menuHandel() {
        binding.menuNamaz.btnMenuBack.visibility = View.GONE
        binding.menuNamaz.btnInfo.visibility = View.VISIBLE
        binding.menuNamaz.txtTitleMenu.text = getString(R.string.namaz)
        binding.menuNamaz.btnInfo.setOnClickListener { findNavController().navigate(R.id.aboutFragment2)}
    }
}
