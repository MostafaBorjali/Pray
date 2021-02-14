package com.borjali.mostafa.pray.presentation.fragment.namaz.content

import android.view.View
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.NamazContentFragmentBinding
import com.borjali.mostafa.pray.domain.model.Pray
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb.NamazVajebFragment.Companion.ARG_PRAY
import timber.log.Timber

class NamazContentFragment : BaseFragment<NamazContentFragmentBinding>() {
    override fun getLayoutResourceId() = R.layout.namaz_content_fragment
    lateinit var pray: Pray

    override fun oncreate() {
        pray = arguments?.getParcelable(ARG_PRAY)!!
        binding.txtContentNamaz.text = HtmlCompat.fromHtml(
            pray.content.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        Timber.d(pray.content.toString())
        menuHandel()
    }

    private fun menuHandel() {
        with(binding.namazContentMenu) {
            txtTitleMenu.text = pray.title
            txtTitleMenu.textSize = 18f
            btnMenuBack.setOnClickListener { findNavController().popBackStack() }
            btnInfo.visibility = View.GONE
        }
    }


}