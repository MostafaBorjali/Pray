package com.borjali.mostafa.pray.ui.fragment.amozesh

import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentAmozeshBinding
import com.borjali.mostafa.pray.ui.base.BaseFragment

class AmozeshFragment : BaseFragment<FragmentAmozeshBinding>() {
    override fun getLayoutResourceId() = R.layout.fragment_amozesh

    override fun oncreate() {
        binding.menuAmozesh.txtTitleMenu.text = "آموزش"
    }

}
