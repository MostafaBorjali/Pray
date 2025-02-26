package com.borjali.mostafa.pray.presentation.fragment.namaz.mostahabi.sublist

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentSublistBinding
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.namaz.adapter.NamazAdapter
import com.borjali.mostafa.pray.presentation.fragment.namaz.mostahabi.NamazMostahabiFragment.PassDataToFragment.LISTOFPRAY
import com.borjali.mostafa.pray.presentation.fragment.namaz.mostahabi.NamazMostahabiFragment.PassDataToFragment.MENU_TITLE
import com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb.NamazVajebFragment.Companion.ARG_POSITION
import com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb.NamazVajebFragment.Companion.ARG_PRAY

class SublistFragment : BaseFragment<FragmentSublistBinding>() {


    override fun getLayoutResourceId() = R.layout.fragment_sublist
    override fun oncreate() {
        if (LISTOFPRAY.isNotEmpty()) {
            binding.recycelerViewSublist.adapter = NamazAdapter(LISTOFPRAY) { _, position ->
                val arg = Bundle()
                arg.putSerializable(ARG_PRAY, LISTOFPRAY[position])
                arg.putInt(ARG_POSITION, position)
                findNavController().navigate(R.id.namazContentFragment, arg)
            }
        }
        menuHandel()

    }

    private fun menuHandel() {
        with(binding.subListMenu) {

            txtTitleMenu.text = MENU_TITLE
            //txtTitleMenu.textSize = 24f
            btnMenuBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }


}


