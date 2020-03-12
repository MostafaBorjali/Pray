@file:Suppress("DEPRECATION")

package com.borjali.mostafa.pray.ui.fragment.rakat_shomar

import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.FragmentDashboardBinding
import com.borjali.mostafa.pray.ui.base.BaseFragment
import com.borjali.mostafa.pray.utils.Data
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {
    private var resID = 0
    private var step = 0
    private lateinit var mSensorManager: SensorManager
    private lateinit var mSensor: Sensor
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var adapter: ButtonAdapter
    override fun getLayoutResourceId() = R.layout.fragment_dashboard

    override fun oncreate() {
        initView()
    }

    private fun initView() {
        Data.position = 10
        val list = ArrayList<String>()
        list.add("اعشاء")
        list.add("مغرب")
        list.add("عصر")
        list.add("ظهر")
        list.add("صبح")
        adapter = ButtonAdapter(list) { recyclerViewItemClicked() }
        binding.recycelerView.adapter = adapter
        resID = resources.getIdentifier("pull_back", "raw", context?.packageName)
        mediaPlayer = MediaPlayer.create(activity, resID)
        mSensorManager = context?.getSystemService(SENSOR_SERVICE) as SensorManager
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
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
                imageViewAnimatedChange(
                    newImage = R.drawable.rakat_shomar_start,
                    rokaatMessage = "",
                    sejdeMessage = ""
                )
            }
            1 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.rakat_shomar_pices_1_1,
                    rokaatMessage = "رکعت اول",
                    sejdeMessage = "سجده اول"
                )

            }
            2 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.rakat_shomar_pices_1_2,
                    rokaatMessage = "رکعت اول",
                    sejdeMessage = "سجده دوم"
                )
            }
            3 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.rakat_shomar_pices_2_1,
                    rokaatMessage = "رکعت دوم ",
                    sejdeMessage = "سجده اول"
                )

            }
            4 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.rakat_shomar_pices_2_2,
                    rokaatMessage = "رکعت دوم",
                    sejdeMessage = "سجده دوم"
                )
            }
            5 -> {
                if (Data.rokaat == 2) {
                    step = 10
                    imageViewAnimatedChange(
                        newImage = R.drawable.rakat_shomar_end,
                        rokaatMessage = "",
                        sejdeMessage = ""
                    )
                } else {
                    imageViewAnimatedChange(
                        newImage = R.drawable.rakat_shomar_pices_3_1,
                        rokaatMessage = "رکعت سوم",
                        sejdeMessage = "سجده اول"
                    )
                }


            }
            6 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.rakat_shomar_pices_3_2,
                    rokaatMessage = "رکعت سوم",
                    sejdeMessage = "سجده دوم"
                )

            }
            7 -> {
                if (Data.rokaat == 3) {
                    step = 10
                    imageViewAnimatedChange(
                        newImage = R.drawable.rakat_shomar_end,
                        rokaatMessage = "",
                        sejdeMessage = ""
                    )
                } else {
                    imageViewAnimatedChange(
                        newImage = R.drawable.rakat_shomar_pices_4_1,
                        rokaatMessage = "رکعت چهارم",
                        sejdeMessage = "سجده اول"
                    )
                }


            }
            8 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.rakat_shomar_pices_4_2,
                    rokaatMessage = "رکعت چهارم",
                    sejdeMessage = "سجده دوم"
                )

            }
            9 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.rakat_shomar_end,
                    rokaatMessage = "",
                    sejdeMessage = ""
                )
            }
            else -> {
            }
        }
    }

    override fun onResume() {
        mSensorManager.registerListener(
            sensListener, mSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
        step = 0
        setStep(step)
        super.onResume()
    }

    override fun onPause() {
        mSensorManager.unregisterListener(sensListener)
        super.onPause()
    }

    private fun imageViewAnimatedChange(
        newImage: Int,
        rokaatMessage: String,
        sejdeMessage: String
    ) {
        val animOut: Animation = AnimationUtils.loadAnimation(context, R.anim.left_to_right_out)
        val animIn: Animation = AnimationUtils.loadAnimation(context, R.anim.left_to_right_in)
        mediaPlayer.start()
        txt_sejde.text = sejdeMessage
        txt_rokaat.text = rokaatMessage
        animOut.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                imgRocaat.setImageResource(newImage)
                animIn.setAnimationListener(object : AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationRepeat(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {}
                })
                imgRocaat.startAnimation(animIn)
            }
        })
        imgRocaat.startAnimation(animOut)
    }

}
