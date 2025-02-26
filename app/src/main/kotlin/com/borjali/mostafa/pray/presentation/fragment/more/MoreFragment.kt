package com.borjali.mostafa.pray.presentation.fragment.more

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.BuildConfig
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentMoreBinding
import com.borjali.mostafa.pray.extention.isConnected
import com.borjali.mostafa.pray.extention.isPackageInstalled
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.rakat_shomar.RakaatShomarFragment.Companion.TASBIHAT
import com.borjali.mostafa.pray.presentation.fragment.rakat_shomar.RakaatShomarFragment.Companion.TITLE


class MoreFragment : BaseFragment<FragmentMoreBinding>() {
    override fun getLayoutResourceId() = R.layout.fragment_more

    override fun oncreate() {
        binding.versionText.text = getString(R.string.version_app, BuildConfig.VERSION_NAME)
        binding.rakaatShomarText.setOnClickListener {
            findNavController().navigate(R.id.rakaatShomarFragment)
        }
        binding.goToRakaatShomar.setOnClickListener {
            findNavController().navigate(R.id.rakaatShomarFragment)
        }
        binding.zekrShomarText.setOnClickListener { goToZeker() }
        binding.goToZekrShomar.setOnClickListener { goToZeker() }
        binding.goToPoint.setOnClickListener { goToPoint() }
        binding.pointText.setOnClickListener { goToPoint() }
        binding.aboutText.setOnClickListener { showBottomSheet() }
        binding.goToAbout.setOnClickListener { showBottomSheet() }
    }

    private fun goToZeker() {
        val bundle = Bundle()
        bundle.putString(TITLE, getString(R.string.zekr_shomar))
        bundle.putBoolean(TASBIHAT, false)
        findNavController().navigate(R.id.zekrShomarFragment, bundle)
    }

    private fun showBottomSheet() {
        AboutBottomSheetDialog().show(childFragmentManager, "ModalBottomSheet")
    }

    private fun goToPoint() {
        if (requireContext().isPackageInstalled(getString(R.string.bazaar_package_name))) {
            try {
                val intent = Intent(Intent.ACTION_EDIT)
                intent.setData(Uri.parse(getString(R.string.bazaar_details_id_com_borjali_mostafa_pray)))
                intent.setPackage(getString(R.string.bazaar_package_name))
                startActivity(intent)
            }catch (e: ActivityNotFoundException){
                installCafe()
            }

        } else {
           installCafe()
        }

    }

    private fun installCafe() {
        if (requireContext().isConnected) {
            val url = getString(R.string.bazaar_url)
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.internet_message), Toast.LENGTH_SHORT
            ).show()
        }
    }

}

