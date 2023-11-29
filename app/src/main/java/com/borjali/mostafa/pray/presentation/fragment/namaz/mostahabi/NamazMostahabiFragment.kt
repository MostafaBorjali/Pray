package com.borjali.mostafa.pray.presentation.fragment.namaz.mostahabi

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.NamazTabFragmentBinding
import com.borjali.mostafa.pray.domain.model.Menu
import com.borjali.mostafa.pray.domain.model.Pray
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.namaz.adapter.MenuMostahabiAdapter
import com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb.NamazVajebFragment.Companion.ARG_PRAY
import org.koin.android.viewmodel.ext.android.viewModel

class NamazMostahabiFragment : BaseFragment<NamazTabFragmentBinding>() {

    private val namazMostahabiViewModel by viewModel<NamazMostahabiViewModel>()

    override fun getLayoutResourceId() = R.layout.namaz_tab_fragment

    override fun oncreate() {
        handelViewModelOperation()
    }

    private fun handelViewModelOperation() {
        with(namazMostahabiViewModel) {
            getListOfNamazMostahabi(1)
            listOfMenuMostahabi.observe(viewLifecycleOwner) {
                binding.recycelerViewMostahabi.adapter =
                    MenuMostahabiAdapter(it as ArrayList<Menu>) { groupId, _ ->
                        getListOfNamaz(groupId)
                    }
            }
            listOfNamazMostahabi.observe(viewLifecycleOwner) { namazList ->
                val args = Bundle()
                if (namazList.isNotEmpty() && namazList.size == 1) {
                    args.putSerializable(ARG_PRAY, namazList[0])
                    findNavController().navigate(R.id.namazContentFragment, args)
                } else if (namazList.isNotEmpty()) {

                    LISTOFPRAY = namazList as ArrayList<Pray>
                    findNavController().navigate(R.id.sublistFragment, args)
                }
            }
        }
    }
    companion object PassDataToFragMent{
         var LISTOFPRAY: ArrayList<Pray>? = null

    }
}

