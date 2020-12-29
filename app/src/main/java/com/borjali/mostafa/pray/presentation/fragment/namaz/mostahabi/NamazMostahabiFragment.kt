package com.borjali.mostafa.pray.presentation.fragment.namaz.mostahabi

import android.widget.Toast
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.NamazMostahabiFragmentBinding
import com.borjali.mostafa.pray.domain.model.Menu
import com.borjali.mostafa.pray.domain.model.Pray
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.namaz.adapter.MenuMostahabiAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class NamazMostahabiFragment : BaseFragment<NamazMostahabiFragmentBinding>() {

    private val namazMostahabiViewModel by viewModel<NamazMostahabiViewModel>()

    override fun getLayoutResourceId() = R.layout.namaz_mostahabi_fragment

    override fun oncreate() {

        with(namazMostahabiViewModel) {
            getListOfNamazMostahabi(1)
            listOfNamazMostahabi.observe(viewLifecycleOwner, {
                binding.recycelerViewMostahabi.adapter =
                    MenuMostahabiAdapter(it as ArrayList<Menu>) { groupId ->
                        Toast.makeText(
                            context,
                            groupId.toString(),
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
            })
        }

    }

}