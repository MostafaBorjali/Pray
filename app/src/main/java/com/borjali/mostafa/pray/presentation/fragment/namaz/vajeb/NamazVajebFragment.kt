package com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.NamazVajebFragmentBinding
import com.borjali.mostafa.pray.domain.model.Menu
import com.borjali.mostafa.pray.domain.model.Pray
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.namaz.adapter.MenuMostahabiAdapter
import org.koin.android.viewmodel.ext.android.viewModel
class NamazVajebFragment : BaseFragment<NamazVajebFragmentBinding>() {
    private val namazVajebViewModel by viewModel<NamazVajebViewModel>()

    override fun getLayoutResourceId() = R.layout.namaz_vajeb_fragment

    override fun oncreate() {
        with(namazVajebViewModel) {
            getListOfMenuNamazVajeb(0)
            listOfNamazMenu.observe(viewLifecycleOwner, {
                binding.recycelerViewMostahabi.adapter =
                    MenuMostahabiAdapter(it as ArrayList<Menu>) { groupId ->
                       getListOfNamaz(groupId)
                    }
            })
            listOfNamazVajeb.observe(viewLifecycleOwner,{namazList ->
                if (namazList.isNotEmpty() &&namazList.size==1){
                    val args = Bundle()
                    args.putString(ARG_PRAY, namazList[0]?.content)
                    findNavController().navigate(R.id.namazContentFragment,args)
                }else if (namazList.isNotEmpty()){
                    Toast.makeText(context,namazList.size.toString(), Toast.LENGTH_LONG).show()
                }
            })
        }


    }

    companion object {
        const val ARG_PRAY = "pray"
    }


}