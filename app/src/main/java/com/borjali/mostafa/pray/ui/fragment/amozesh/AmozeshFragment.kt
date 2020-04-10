package com.borjali.mostafa.pray.ui.fragment.amozesh

import android.view.View
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentAmozeshBinding
import com.borjali.mostafa.pray.ui.base.BaseFragment

class AmozeshFragment : BaseFragment<FragmentAmozeshBinding>() {
    override fun getLayoutResourceId() = R.layout.fragment_amozesh

    override fun oncreate() {
        menuHandel()
    }

    private fun menuHandel() {
        binding.menuAmozesh.btnMenuBack.visibility = View.GONE
        binding.menuAmozesh.btnInfo.visibility = View.VISIBLE
        binding.menuAmozesh.txtTitleMenu.text = getString(R.string.amoozesh)
        binding.menuAmozesh.btnInfo.setOnClickListener { findNavController().navigate(R.id.aboutFragment) }
    }
}
