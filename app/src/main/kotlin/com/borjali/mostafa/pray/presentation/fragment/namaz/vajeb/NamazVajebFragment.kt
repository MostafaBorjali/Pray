package com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentNamazTabBinding
import com.borjali.mostafa.pray.domain.model.Menu
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.namaz.adapter.MenuAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NamazVajebFragment : BaseFragment<FragmentNamazTabBinding>() {
    private val namazVajebViewModel by viewModel<NamazVajebViewModel>()
    override fun getLayoutResourceId() = R.layout.fragment_namaz_tab

    override fun oncreate() {
        viewModelOperation()
    }

    private fun viewModelOperation() {
        with(namazVajebViewModel) {
            getListOfMenuNamazVajeb(0)
            listOfNamazMenu.observe(viewLifecycleOwner) {
                binding.recycelerViewMostahabi.adapter =
                    MenuAdapter(it as ArrayList<Menu>) { groupId, _ ->
                        getListOfNamaz(groupId)
                    }
            }
            listOfNamazVajeb.observe(viewLifecycleOwner) { namazList ->

                if (namazList.isNotEmpty() && namazList.size == 1) {
                    val args = Bundle()
                    args.putSerializable(ARG_PRAY, namazList[0])
                    args.putInt(ARG_POSITION, 0)
                    findNavController().navigate(R.id.namazContentFragment, args)
                } else if (namazList.isNotEmpty()) {
                    Toast.makeText(context, namazList.size.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {
        const val ARG_PRAY = "pray"
        const val ARG_POSITION = "position"
    }


}