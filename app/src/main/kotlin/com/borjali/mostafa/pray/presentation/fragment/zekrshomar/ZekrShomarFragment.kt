package com.borjali.mostafa.pray.presentation.fragment.zekrshomar

import android.annotation.SuppressLint
import android.view.View
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentZekrShomarBinding
import com.borjali.mostafa.pray.extention.longVibratePhone
import com.borjali.mostafa.pray.extention.shortVibratePhone
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.rakat_shomar.RakaatShomarFragment.Companion.TASBIHAT
import com.borjali.mostafa.pray.presentation.fragment.rakat_shomar.RakaatShomarFragment.Companion.TITLE

@SuppressLint("SetTextI18n")
class ZekrShomarFragment : BaseFragment<FragmentZekrShomarBinding>() {
    var zekerNumber = 0
    var alhamd = 0
    var sobhanallah = 0
    var isTasbihat = false
    override fun getLayoutResourceId() = R.layout.fragment_zekr_shomar

    override fun oncreate() {
        arguments?.getString(TITLE)?.let {
            binding.menuZekrshomar.txtTitleMenu.text = it
        }
        arguments?.getBoolean(TASBIHAT)?.let {
            isTasbihat = it
        }
        if (isTasbihat) {
            binding.imageZeker.setImageResource(R.drawable.ic_allah)
        }

        binding.txtShomaresh.text = zekerNumber.toString()
        binding.btnAddZekr.setOnClickListener {
            zekerNumber += 1
            if (isTasbihat) {
                tasbihatOperation(zekerNumber)
            } else {
                zekrOperation(zekerNumber)
            }
        }
        binding.menuZekrshomar.btnMenuBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun zekrOperation(zekerNumber: Int) {
        if (zekerNumber <= 1000) {
            binding.txtShomaresh.text = zekerNumber.toString()
            shortVibratePhone()
        } else {
            binding.imageZeker.visibility = View.INVISIBLE
            binding.txtShomaresh.text = getString(R.string.end_message)
            longVibratePhone()
        }
    }

    private fun tasbihatOperation(zekerNumber: Int) {
        if (zekerNumber <= 100) {

            binding.txtShomaresh.text = zekerNumber.toString()
            when (zekerNumber) {
                34 -> {
                    longVibratePhone()
                }

                35 -> {
                    alhamd = 1
                    binding.imageZeker.setImageResource(R.drawable.ic_alhamd)
                    binding.txtShomaresh.text = alhamd.toString()
                    shortVibratePhone()
                }

                in 36..66 -> {
                    alhamd += 1
                    binding.txtShomaresh.text = alhamd.toString()
                    shortVibratePhone()
                }

                67 -> {
                    alhamd += 1
                    binding.txtShomaresh.text = alhamd.toString()
                    longVibratePhone()
                }

                in 68..100 -> {
                    binding.imageZeker.setImageResource(R.drawable.ic_sobhanallah)
                    sobhanallah += 1
                    binding.txtShomaresh.text = sobhanallah.toString()
                    shortVibratePhone()
                }

                else -> {
                    shortVibratePhone()
                }
            }
        } else {
            binding.imageZeker.visibility = View.INVISIBLE
            binding.txtShomaresh.text = "پایان"
            longVibratePhone()
        }
    }


}
