package com.borjali.mostafa.pray.presentation.fragment.namaz.mostahabi

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentNamazTabBinding
import com.borjali.mostafa.pray.domain.model.Menu
import com.borjali.mostafa.pray.domain.model.Pray
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.namaz.adapter.MenuAdapter
import com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb.NamazVajebFragment.Companion.ARG_PRAY
import org.koin.androidx.viewmodel.ext.android.viewModel

class NamazMostahabiFragment : BaseFragment<FragmentNamazTabBinding>() {

    private val namazMostahabiViewModel by viewModel<NamazMostahabiViewModel>()

    override fun getLayoutResourceId() = R.layout.fragment_namaz_tab

    override fun oncreate() {
        handelViewModelOperation()
    }

    private fun handelViewModelOperation() {
        with(namazMostahabiViewModel) {
            getListOfNamazMostahabi(1)
            listOfMenuMostahabi.observe(viewLifecycleOwner) {
                binding.recycelerViewMostahabi.adapter =
                    MenuAdapter(it as ArrayList<Menu>) { groupId, position ->
                        getListOfNamaz(groupId)
                        MENU_TITLE = it[position].groupTitle.toString()
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

    companion object PassDataToFragment {
        var LISTOFPRAY = arrayListOf<Pray>()
        var MENU_TITLE = ""

    }
}

