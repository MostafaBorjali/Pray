package com.borjali.mostafa.pray.presentation.fragment.rakat_shomar

import android.app.Dialog
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.LayoutAboutBinding
import com.borjali.mostafa.pray.databinding.LayoutShortcutBinding
import com.borjali.mostafa.pray.presentation.activity.MainActivity
import com.google.android.material.R.id.design_bottom_sheet

import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ShortcutBottomSheetDialog : BottomSheetDialogFragment() {

    lateinit var binding: LayoutShortcutBinding
    private lateinit var dialog: BottomSheetDialog
    private lateinit var behavior: BottomSheetBehavior<View>


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTheme)
        dialog.setOnShowListener {
            val d = it as BottomSheetDialog
            val sheet =
                d.findViewById<View>(design_bottom_sheet) as View
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
            DataBindingUtil.inflate(inflater, R.layout.layout_shortcut, container, false)

        binding.txtClose.setOnClickListener { dismiss() }
        binding.cancel.setOnClickListener { dismiss() }
        binding.confirm.setOnClickListener { createShortcut() }

        return binding.root
    }
    private fun createShortcut() {
        val intent = Intent(context, MainActivity::class.java).apply {
            action = Intent.ACTION_VIEW
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("shortcut_type", "rakaat_shomar")
            putExtra("open_direct", true)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val shortcutManager = requireContext().getSystemService(ShortcutManager::class.java)

            if (shortcutManager != null && shortcutManager.isRequestPinShortcutSupported) {
                val shortcutInfo = ShortcutInfo.Builder(
                    requireContext(),
                    "rakaat_shomar_${System.currentTimeMillis()}"
                )
                    .setShortLabel("رکعت شمار")
                    .setLongLabel("اپلیکیشن نمازهای مستحبی")
                    .setIcon(Icon.createWithResource(requireContext(), R.drawable.ic_rakat_shomar))
                    .setIntent(intent)
                    .build()

                // Request the shortcut to be pinned
                shortcutManager.requestPinShortcut(shortcutInfo, null)
            } else {
                Toast.makeText(
                    requireContext(),
                    "این دستگاه از شورت‌ کات پشتیبانی نمی‌کند",
                    Toast.LENGTH_LONG
                ).show()
            }
        }else{
            createShortcutWithCompat(intent)
        }
        dismiss()
    }
    @Suppress("DEPRECATION")
    fun createShortcutWithCompat(intent: Intent) {

        val shortcutIntent = Intent("com.android.launcher.action.INSTALL_SHORTCUT")
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent)
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "رکعت شمار")
        shortcutIntent.putExtra(
            Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
            Intent.ShortcutIconResource.fromContext(
                requireContext(),
                R.drawable.ic_rakat_shomar
            )
        )
        shortcutIntent.putExtra("duplicate", false)

        requireContext().sendBroadcast(shortcutIntent)

        Toast.makeText(
            requireContext(),
            "دسترسی برای رکعت شمار ایجاد شد. صفحه اصلی را بررسی کنید.",
            Toast.LENGTH_LONG
        ).show()
    }
}