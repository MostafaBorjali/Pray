package com.borjali.mostafa.pray.presentation.fragment.amozesh

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentAmozeshBinding
import com.borjali.mostafa.pray.domain.model.Menu
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.namaz.adapter.MenuAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AmozeshFragment : BaseFragment<FragmentAmozeshBinding>() {
    private val viewModel by viewModel<AmozeshViewModel>()
    override fun getLayoutResourceId() = R.layout.fragment_amozesh

    override fun oncreate() {
        viewModelOperation()
    }

    private fun viewModelOperation() {
        with(viewModel) {
            getListOfMenu(2)
            listOfMenu.observe(viewLifecycleOwner) {
                binding.recycelerViewAmozesh.adapter =
                    MenuAdapter(it as ArrayList<Menu>) { groupId, position ->
                        val args = Bundle()
                        args.putInt(GROUP_ID, groupId)
                        args.putString(TITLE, it[position].groupTitle)
                        findNavController().navigate(R.id.playerFragment, args)
                    }
            }
        }
    }



    companion object {
        const val GROUP_ID = "groupId"
        const val TITLE = "title"
    }
}


