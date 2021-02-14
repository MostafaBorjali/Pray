package com.borjali.mostafa.pray.presentation.fragment.namaz.mostahabi.sublist

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.SublistFragmentBinding
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.namaz.adapter.NamazAdapter
import com.borjali.mostafa.pray.presentation.fragment.namaz.mostahabi.NamazMostahabiFragment.PassDataToFragMent.LISTOFPRAY
import com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb.NamazVajebFragment.Companion.ARG_PRAY

class SublistFragment : BaseFragment<SublistFragmentBinding>() {


    override fun getLayoutResourceId() = R.layout.sublist_fragment
    override fun oncreate() {
        if (LISTOFPRAY.isNotEmpty() && LISTOFPRAY.size > 0) {
            binding.recycelerViewSublist.adapter = NamazAdapter(LISTOFPRAY) { _, position ->
                val arg = Bundle()
                arg.putParcelable(ARG_PRAY, LISTOFPRAY[position])
                findNavController().navigate(R.id.namazContentFragment, arg)
            }
        }
        menuHandel()

    }

    private fun menuHandel() {
        with(binding.subListMenu) {
            btnInfo.visibility = View.GONE
            txtTitleMenu.text = LISTOFPRAY[0].title
            txtTitleMenu.textSize = 18f
            btnMenuBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }


}


