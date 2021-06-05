package com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb

import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.NamazTabFragmentBinding
import com.borjali.mostafa.pray.domain.model.Menu
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.namaz.adapter.MenuMostahabiAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class NamazVajebFragment : BaseFragment<NamazTabFragmentBinding>() {
    private val namazVajebViewModel by viewModel<NamazVajebViewModel>()
    override fun getLayoutResourceId() = R.layout.namaz_tab_fragment

    override fun oncreate() {
        viewModelOperation()
    }

    private fun viewModelOperation() {
        with(namazVajebViewModel) {
            getListOfMenuNamazVajeb(0)
            listOfNamazMenu.observe(viewLifecycleOwner, {
                binding.recycelerViewMostahabi.adapter =
                    MenuMostahabiAdapter(it as ArrayList<Menu>) { groupId, _ ->
                        getListOfNamaz(groupId)
                    }
            })
            listOfNamazVajeb.observe(viewLifecycleOwner, { namazList ->
                Timber.e(namazList.toString())
                if (namazList.isNotEmpty() && namazList.size == 1) {
                    val args = Bundle()
                    args.putParcelable(ARG_PRAY, namazList[0])
                    args.putInt(ARG_POSITION, 0)
                    findNavController().navigate(R.id.namazContentFragment, args)
                } else if (namazList.isNotEmpty()) {
                    Toast.makeText(context, namazList.size.toString(), Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    companion object {
        const val ARG_PRAY = "pray"
        const val ARG_POSITION = "position"
    }


}