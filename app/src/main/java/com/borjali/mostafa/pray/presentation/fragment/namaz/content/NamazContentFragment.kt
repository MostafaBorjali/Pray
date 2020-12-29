package com.borjali.mostafa.pray.presentation.fragment.namaz.content

import android.text.Html
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.NamazContentFragmentBinding
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb.NamazVajebFragment.Companion.ARG_PRAY

class NamazContentFragment : BaseFragment<NamazContentFragmentBinding>() {
    override fun getLayoutResourceId() = R.layout.namaz_content_fragment

    override fun oncreate() {
       binding.txtContentNamaz.text=Html.fromHtml(arguments?.getString(ARG_PRAY))
    }


}