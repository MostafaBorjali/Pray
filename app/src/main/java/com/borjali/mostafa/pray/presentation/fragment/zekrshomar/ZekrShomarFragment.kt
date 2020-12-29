package com.borjali.mostafa.pray.presentation.fragment.zekrshomar

import android.view.View
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentZekrShomarBinding
import com.borjali.mostafa.pray.extention.longVibratePhone
import com.borjali.mostafa.pray.extention.shortVibratePhone
import com.borjali.mostafa.pray.presentation.base.BaseFragment


class ZekrShomarFragment : BaseFragment<FragmentZekrShomarBinding>() {
    var zekerNumber = 0
    var alhamd = 0
    var sobhanallah = 0
    override fun getLayoutResourceId() = R.layout.fragment_zekr_shomar

    override fun oncreate() {
        binding.txtShomaresh.text = zekerNumber.toString()
        binding.btnAddZekr.setOnClickListener {
            zekerNumber += 1
            zekrOperation(zekerNumber)
        }
        binding.menuZekrshomar.btnMenuBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun zekrOperation(zekerNumber: Int) {
        if (zekerNumber <= 100) {

            binding.txtShomaresh.text = zekerNumber.toString()
            when (zekerNumber) {
                34 -> {
                    longVibratePhone()
                }
                35 -> {
                    alhamd=1
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
