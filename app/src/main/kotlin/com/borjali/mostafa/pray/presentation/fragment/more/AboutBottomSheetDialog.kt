package com.borjali.mostafa.pray.presentation.fragment.more

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.LayoutAboutBinding

import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AboutBottomSheetDialog : BottomSheetDialogFragment() {

    lateinit var binding: LayoutAboutBinding
    private lateinit var dialog: BottomSheetDialog
    private lateinit var behavior: BottomSheetBehavior<View>


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTheme)
        dialog.setOnShowListener {
            val d = it as BottomSheetDialog
            val sheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as View
            behavior = BottomSheetBehavior.from(sheet)
            behavior.isHideable = false
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.layout_about, container, false)
        binding.txtMessage.text = getString(R.string.about_message)
        binding.txtClose.setOnClickListener { dismiss() }
        binding.sendEmail.setOnClickListener { sendEmail() }

        return binding.root
    }

    private fun sendEmail() {
        try {
            val selectorIntent = Intent(Intent.ACTION_SENDTO)
            selectorIntent.data = Uri.parse(getString(R.string.mail_to))

            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.my_gmail)))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.gmail_subject))
            emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.gmail_body))
            emailIntent.selector = selectorIntent

            requireContext().startActivity(
                Intent.createChooser(
                    emailIntent,
                    getString(R.string.send_email)
                )
            )
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                getString(R.string.error_send_email),
                Toast.LENGTH_SHORT
            ).show()
        }

    }


}