package com.borjali.mostafa.pray.presentation.fragment.amozesh

import android.view.View
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentAmozeshBinding
import com.borjali.mostafa.pray.presentation.base.BaseFragment

class AmozeshFragment : BaseFragment<FragmentAmozeshBinding>() {
    override fun getLayoutResourceId() = R.layout.fragment_amozesh

    override fun oncreate() {
        menuHandel()
    }

    private fun menuHandel() {
        with(binding.menuAmozesh){
            btnMenuBack.visibility = View.GONE
            btnInfo.visibility = View.GONE
            txtTitleMenu.text = getString(R.string.amoozesh)
            btnInfo.setOnClickListener { findNavController().navigate(R.id.aboutFragment) }
        }
    }
}
