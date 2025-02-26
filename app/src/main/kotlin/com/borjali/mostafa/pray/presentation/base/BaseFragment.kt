package com.borjali.mostafa.pray.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver


abstract class BaseFragment<B : ViewDataBinding> : Fragment(), LifecycleObserver {
    lateinit var binding: B
    private var vieww: View? = null

    /*    val ms: MyShared by inject()
        val response: ApiResponce by inject()*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (vieww == null) {
            binding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)

            vieww = binding.root

            oncreate()
        }

        return binding.root
    }

    protected abstract fun getLayoutResourceId(): Int
    protected abstract fun oncreate()
}
