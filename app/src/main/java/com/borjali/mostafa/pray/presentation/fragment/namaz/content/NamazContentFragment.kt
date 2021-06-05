package com.borjali.mostafa.pray.presentation.fragment.namaz.content

import android.annotation.SuppressLint
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.core.text.toSpanned
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.NamazContentFragmentBinding
import com.borjali.mostafa.pray.domain.model.Pray
import com.borjali.mostafa.pray.extention.setTextAnimation
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.namaz.mostahabi.NamazMostahabiFragment.PassDataToFragMent.LISTOFPRAY
import com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb.NamazVajebFragment.Companion.ARG_POSITION
import com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb.NamazVajebFragment.Companion.ARG_PRAY
import com.borjali.mostafa.pray.utils.OnSwipeTouchListener
import timber.log.Timber

class NamazContentFragment : BaseFragment<NamazContentFragmentBinding>() {
    override fun getLayoutResourceId() = R.layout.namaz_content_fragment
    lateinit var pray: Pray
    private var position = 0

    override fun oncreate() {
        initParam()
        menuHandel()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initParam() {
        pray = arguments?.getParcelable(ARG_PRAY)!!
        position = arguments?.getInt(ARG_POSITION)!!
        binding.txtContentNamaz.text = HtmlCompat.fromHtml(
            pray.content.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        Timber.d(pray.content.toString())
        onClickScreen()
        binding.nextPrayButton.setOnClickListener { swipeLeft() }
        binding.pervisionPrayButton.setOnClickListener { swipeRight() }
        binding.scrollView2.setOnTouchListener(object :
            OnSwipeTouchListener(binding.scrollView2.context) {
            override fun onSwipeRight() {
                swipeRight()
                super.onSwipeRight()
            }

            override fun onSwipeLeft() {
                swipeLeft()
                super.onSwipeLeft()
            }

            override fun onClick() {
                onClickScreen()
                super.onClick()
            }
        })
        binding.txtContentNamaz.setOnTouchListener(object :
            OnSwipeTouchListener(binding.txtContentNamaz.context) {
            override fun onSwipeRight() {
                swipeRight()
                super.onSwipeRight()
            }

            override fun onSwipeLeft() {
                swipeLeft()
                super.onSwipeLeft()
            }

            override fun onClick() {
                onClickScreen()
                super.onClick()
            }
        })
    }

    private fun menuHandel() {
        with(binding.namazContentMenu) {
            txtTitleMenu.text = pray.title
            txtTitleMenu.textSize = 18f
            btnMenuBack.setOnClickListener { findNavController().popBackStack() }
            btnInfo.visibility = View.GONE
        }
    }

    private fun swipeLeft() {
        if (!LISTOFPRAY.isNullOrEmpty() && position <= LISTOFPRAY!!.size) {
            position++
            binding.txtContentNamaz.setTextAnimation(
                HtmlCompat.fromHtml(
                    LISTOFPRAY!![position].content.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT
                )
            )
            binding.namazContentMenu.txtTitleMenu.setTextAnimation(LISTOFPRAY!![position].title!!.toSpanned())

        }

    }

    private fun swipeRight() {

        if (!LISTOFPRAY.isNullOrEmpty() && position > 0) {
            position--

            binding.txtContentNamaz.setTextAnimation(
                HtmlCompat.fromHtml(
                    LISTOFPRAY!![position].content.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT
                )
            )
            binding.namazContentMenu.txtTitleMenu.setTextAnimation(LISTOFPRAY!![position].title!!.toSpanned())
        }

    }

    private fun onClickScreen() {
        Timber.d("onClickedScreen")
        if (!LISTOFPRAY.isNullOrEmpty() && LISTOFPRAY?.size!! > 1) {
            if (binding.nextPrayButton.isVisible) {
                binding.nextPrayButton.visibility = View.GONE
                binding.pervisionPrayButton.visibility = View.GONE
            } else {
                binding.nextPrayButton.visibility = View.VISIBLE
                binding.pervisionPrayButton.visibility = View.VISIBLE
            }
        } else {
            binding.nextPrayButton.visibility = View.GONE
            binding.pervisionPrayButton.visibility = View.GONE
        }

    }

}