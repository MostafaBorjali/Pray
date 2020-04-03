package com.borjali.mostafa.pray.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.borjali.mostafa.pray.R
import kotlinx.android.synthetic.main.custom_tabbar.view.*

class CustomTabBar : LinearLayout {


    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private var leftSelected: (() -> Unit)? = null
    private var rightSelected: (() -> Unit)? = null
    private var centerSelected: (() -> Unit)? = null

    private var mRootView: View? = null

    private fun init(context: Context, set: AttributeSet?) {
        mRootView = View.inflate(context, R.layout.custom_tabbar, this)
        homeSelected()
        profile.setOnClickListener {
            profileSelected()
        }
        home.setOnClickListener {
            homeSelected()
        }
        payment.setOnClickListener {
            paymentSelected()
        }
    }

    fun goTabOnHome(){
        hideProfile()
        hidePayment()
        showHome()
        tabbarMotion.center()
    }

    private fun profileSelected() {
        rightSelected?.invoke()
        showProfile()
        hidePayment()
        hideHome()
        tabbarMotion.right()

    }

    private fun homeSelected() {
        centerSelected?.invoke()
        hideProfile()
        hidePayment()
        showHome()
        tabbarMotion.center()

    }

    private fun paymentSelected() {
        leftSelected?.invoke()
        hideProfile()
        showPayment()
        hideHome()
        tabbarMotion.left()

    }

    private fun hideProfile() {
        profile_circle.visibility = View.GONE
        profile_icon.visibility = View.VISIBLE
        profile_text?.visibility = View.GONE
    }

    private fun hideHome() {
        home_circle.visibility = View.GONE
        home_icon.visibility = View.VISIBLE
        home_text?.visibility = View.GONE
    }

    private fun hidePayment() {
        payment_circle.visibility = View.GONE
        payment_icone.visibility = View.VISIBLE
        payment_text?.visibility = View.GONE
    }

    private fun showProfile() {
        profile_circle.visibility = View.VISIBLE
        profile_icon.visibility = View.GONE
        profile_text?.visibility = View.VISIBLE
    }

    private fun showHome() {
        home_circle.visibility = View.VISIBLE
        home_icon.visibility = View.GONE
        home_text?.visibility = View.VISIBLE
    }

    private fun showPayment() {
        payment_circle.visibility = View.VISIBLE
        payment_icone.visibility = View.GONE
        payment_text?.visibility = View.VISIBLE
    }


    fun right(right: (() -> Unit)?) {
        rightSelected = right
    }

    fun left(left: (() -> Unit)?) {
        leftSelected = left
    }

    fun center(center: (() -> Unit)?) {
        centerSelected = center
    }


}