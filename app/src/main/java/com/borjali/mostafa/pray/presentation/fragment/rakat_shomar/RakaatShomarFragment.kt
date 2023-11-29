@file:Suppress("DEPRECATION")

package com.borjali.mostafa.pray.presentation.fragment.rakat_shomar

import android.annotation.SuppressLint
import android.content.Context.SENSOR_SERVICE
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentRakaatShomarBinding
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb.NamazVajebFragment
import com.borjali.mostafa.pray.utils.Data
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


class RakaatShomarFragment : BaseFragment<FragmentRakaatShomarBinding>() {
    private var resID = 0
    private var step = 0
    private lateinit var mSensorManager: SensorManager
    private lateinit var mSensor: Sensor
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var adapter: ButtonAdapter
    private val rakaatShomarViewModel by viewModel<RakaatShomarViewModel>()


    override fun getLayoutResourceId() = R.layout.fragment_rakaat_shomar

    @SuppressLint("SourceLockedOrientationActivity")
    override fun oncreate() {
        activity?.apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        initView()
    }

    private fun initView() {
        Data.position = 10
        val list = ArrayList<String>()
        list.add(getString(R.string.asha))
        list.add(getString(R.string.maghreb))
        list.add(getString(R.string.asr))
        list.add(getString(R.string.zohr))
        list.add(getString(R.string.sobh))
        adapter = ButtonAdapter(list) { recyclerViewItemClicked() }
        binding.recycelerView.adapter = adapter
        resID = resources.getIdentifier(
            getString(R.string.pull_back),
            getString(R.string.raw),
            context?.packageName
        )
        mediaPlayer = MediaPlayer.create(activity, resID)
        mSensorManager = context?.getSystemService(SENSOR_SERVICE) as SensorManager
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)!!
        binding.tasbihat.setOnClickListener { findNavController().navigate(R.id.zekrShomarFragment) }
        binding.taaghibat.setOnClickListener {
            rakaatShomarViewModel.getListOfNamaz(Data.typeOFPray)
            Timber.e("clicked")
        }
        viewModelOperation()
    }

    private fun viewModelOperation() {
        with(rakaatShomarViewModel) {
            listOfTaaghibat.observeForever {
                Timber.e(binding.taaghibat.toString())
                if (it!!.isNotEmpty() && it.size == 1) {
                    val args = Bundle()
                    args.putSerializable(NamazVajebFragment.ARG_PRAY, it[0])
                    findNavController().navigate(R.id.contentTaghibat, args)
                } else if (it.isNotEmpty()) {
                    Toast.makeText(context, it.size.toString(), Toast.LENGTH_LONG).show()
                }
            }
            
        }
    }

    private fun recyclerViewItemClicked() {
        mediaPlayer.start()
        step = 0
        setStep(0)
        adapter.notifyDataSetChanged()
    }

    private val sensListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            if (event.values[0] < mSensor.maximumRange) {
                setStep(++step)
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }

    private fun setStep(currentState: Int) {
        when (currentState) {
            0 -> {
                binding.tasbihat.visibility = View.GONE
                binding.taaghibat.visibility = View.GONE
                imageViewAnimatedChange(
                    newImage = R.drawable.rakat_shomar_start,
                    rakaatMessage = "",
                    sejdeMessage = ""
                )
            }
            1 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.rakat_shomar_pices_1_1,
                    rakaatMessage = getString(R.string.rakat_aval),
                    sejdeMessage = getString(R.string.sajde_aval)
                )

            }
            2 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.rakat_shomar_pices_1_2,
                    rakaatMessage = getString(R.string.rakat_aval),
                    sejdeMessage = getString(R.string.sajde_dowom)
                )
            }
            3 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.rakat_shomar_pices_2_1,
                    rakaatMessage = getString(R.string.rakatDowom),
                    sejdeMessage = getString(R.string.sajde_aval)
                )

            }
            4 -> {
                if (Data.numberOFRokaat == 2) {
                    imageViewAnimatedChange(
                        newImage = R.drawable.ic_namaz,
                        rakaatMessage = getString(R.string.salame_namaz),
                        sejdeMessage = ""
                    )
                    binding.tasbihat.visibility = View.VISIBLE
                    binding.taaghibat.visibility = View.VISIBLE

                } else {
                    imageViewAnimatedChange(
                        newImage = R.drawable.ic_namaz,
                        rakaatMessage = getString(R.string.rakatDowom),
                        sejdeMessage = getString(R.string.tashhod)
                    )
                }

            }
            5 -> {
                if (Data.numberOFRokaat == 2) {
                    step = 10
                    imageViewAnimatedChange(
                        newImage = R.drawable.rakat_shomar_end,
                        rakaatMessage = "",
                        sejdeMessage = ""
                    )
                    binding.tasbihat.visibility = View.GONE
                    binding.taaghibat.visibility = View.GONE

                } else {
                    imageViewAnimatedChange(
                        newImage = R.drawable.rakat_shomar_pices_3_1,
                        rakaatMessage = getString(R.string.rakat_sewom),
                        sejdeMessage = getString(R.string.sajde_aval)
                    )
                }


            }
            6 -> {
                if (Data.numberOFRokaat == 3) {
                    step = 10
                    imageViewAnimatedChange(
                        newImage = R.drawable.ic_namaz,
                        rakaatMessage = getString(R.string.salame_namaz),
                        sejdeMessage = ""
                    )
                    binding.tasbihat.visibility = View.VISIBLE
                    binding.taaghibat.visibility = View.VISIBLE

                } else {
                    imageViewAnimatedChange(
                        newImage = R.drawable.rakat_shomar_pices_3_2,
                        rakaatMessage = getString(R.string.rakat_sewom),
                        sejdeMessage = getString(R.string.sajde_dowom)
                    )
                }


            }
            7 -> {
                if (Data.numberOFRokaat == 3) {
                    step = 10
                    imageViewAnimatedChange(
                        newImage = R.drawable.rakat_shomar_end,
                        rakaatMessage = "",
                        sejdeMessage = ""
                    )
                    binding.tasbihat.visibility = View.GONE
                    binding.taaghibat.visibility = View.GONE
                } else {
                    imageViewAnimatedChange(
                        newImage = R.drawable.rakat_shomar_pices_4_1,
                        rakaatMessage = getString(R.string.rakat_chaharom),
                        sejdeMessage = getString(R.string.sajde_aval)
                    )
                }

            }
            8 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.ic_namaz,
                    rakaatMessage = getString(R.string.salame_namaz),
                    sejdeMessage = ""
                )
                binding.tasbihat.visibility = View.VISIBLE
                if (Data.typeOFPray != 0) {
                    binding.taaghibat.visibility = View.VISIBLE
                }

            }
            9 -> {
                binding.tasbihat.visibility = View.GONE
                binding.taaghibat.visibility = View.GONE
                imageViewAnimatedChange(
                    newImage = R.drawable.rakat_shomar_end,
                    rakaatMessage = "",
                    sejdeMessage = ""
                )
            }
            else -> {
            }
        }
    }

    override fun onResume() {
        binding.txtSejde.isEnabled = false
        mSensorManager.registerListener(
            sensListener, mSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
        setStep(step)
        super.onResume()
    }

    override fun onPause() {
        mSensorManager.unregisterListener(sensListener)
        super.onPause()
    }

    private fun imageViewAnimatedChange(
        newImage: Int,
        rakaatMessage: String,
        sejdeMessage: String
    ) {
        val animOut: Animation = AnimationUtils.loadAnimation(context, R.anim.left_to_right_out)
        val animIn: Animation = AnimationUtils.loadAnimation(context, R.anim.left_to_right_in)
        mediaPlayer.start()
        binding.txtSejde.text = sejdeMessage
        binding.txtRokaat.text = rakaatMessage
        animOut.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                binding.imgRocaat.setImageResource(newImage)
                animIn.setAnimationListener(object : AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationRepeat(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {}
                })
                binding.imgRocaat.startAnimation(animIn)
            }
        })
        binding.imgRocaat.startAnimation(animOut)
    }

}
