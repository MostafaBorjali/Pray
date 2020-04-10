package com.borjali.mostafa.pray.ui.fragment.about

import android.annotation.SuppressLint
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentAboutBinding
import com.borjali.mostafa.pray.ui.base.BaseFragment

class AboutFragment : BaseFragment<FragmentAboutBinding>() {
    override fun getLayoutResourceId() = R.layout.fragment_about


    override fun oncreate() {
        menuHandel()
}
    @SuppressLint("SetTextI18n")
    private fun menuHandel() {
        binding.textView3.text = " کاربر گرامی\n" +
                "این برنامه به یاری خداوند در حال تکمیل شدن می باشد \n" +
                " و قرار است در بروز رسانی ها بخش هایی نظیر:\n" +
                "لیست و کیفیت خواندن نماز های واجب \n" +
                " لیست و کیفیت خواندن نماز های مستحبی\n" +
                "آموزش احکام و روش صحیح خوانی نمازها \n" +
                "این برنامه کاملاً رایگان بوده و درصورت امکان ما را از دعای خیرتان محروم نفرمایید\n" +
                " با تشکر\n" +
                "\t \t سید علی موسوی و مصطفی برجعلی \n"+
                "نظرات و انتقادات خود را به ایمیل زیر ارسال نمایید \n" +
                "Mostafa.borjali.68@gmail.com"
        binding.menuAbout.btnMenuBack.setOnClickListener { findNavController().popBackStack() }
        binding.menuAbout.txtTitleMenu.text = "درباره ما"
    }
    }

