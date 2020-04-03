package com.borjali.mostafa.pray.ui.fragment.zekrshomar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentZekrShomarBinding
import com.borjali.mostafa.pray.ui.activity.MainActivity
import com.borjali.mostafa.pray.ui.base.BaseFragment

class ZekrShomarFragment : BaseFragment<FragmentZekrShomarBinding>() {
    override fun getLayoutResourceId() = R.layout.fragment_zekr_shomar

    override fun oncreate() {
        val mainActivity = MainActivity()
      //  mainActivity.supportActionBar?.show()
    }


}
