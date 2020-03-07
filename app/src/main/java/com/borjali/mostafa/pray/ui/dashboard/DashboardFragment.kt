@file:Suppress("DEPRECATION")

package com.borjali.mostafa.pray.ui.dashboard
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.borjali.mostafa.pray.R
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.util.*


class DashboardFragment : Fragment() {
    private var resID: Int = 0
    private val waitTime: Long = 1000
    private var step = 0
    private var waitingTime: Long = 0
    private var isNear = false
    private lateinit var mSensorManager: SensorManager
    private var mSensor: Sensor? = null

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initView()
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        return inflater.inflate(R.layout.fragment_dashboard, container, false)


    }

    private fun initView() {
        resID = resources.getIdentifier("knob", "raw", context?.packageName)
        mediaPlayer = MediaPlayer.create(activity, resID)
        mSensorManager = context?.getSystemService(SENSOR_SERVICE) as SensorManager
        Handler().postDelayed({
                mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
                mSensorManager.registerListener(
                    sensListener,
                    mSensor,
                    SensorManager.SENSOR_DELAY_NORMAL
                )
                step = 0
                setStep(step)
            }, 300)
    }
    private val sensListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            if (event.values[0] == 0.toFloat()) {
                if (waitingTime < getTime()) isNear = true
            } else {
                if (isNear) {
                    isNear = false
                    setStep(++step)
                    waitingTime = getTime() + waitTime
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }
    private fun setStep(currentState: Int): Boolean {

        when (currentState) {
            0 -> {
                imgRocaat.setImageResource(R.drawable.alah)
            }
            1 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.ic_1_1,
                    rokaatMessage = "رکعت اول",
                    sejdeMessage = "سجده اول"
                )

            }
            2 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.ic_1_2,
                    rokaatMessage = "رکعت اول",
                    sejdeMessage = "سجده دوم"
                )
            }
            3 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.ic_2_1,
                    rokaatMessage = "رکعت دوم ",
                    sejdeMessage = "سجده اول"
                )
            }
            4 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.ic_2_2,
                    rokaatMessage = "رکعت دوم",
                    sejdeMessage = "سجده دوم"
                )
            }
            5 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.ic_3_1,
                    rokaatMessage = "رکعت سوم",
                    sejdeMessage = "سجده اول"
                )

            }
            6 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.ic_3_2,
                    rokaatMessage = "رکعت سوم",
                    sejdeMessage = "سجده دوم"
                )

            }
            7 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.ic_4_1,
                    rokaatMessage = "رکعت چهارم",
                    sejdeMessage = "سجده اول"
                )

            }
            8 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.ic_4_2,
                    rokaatMessage = "رکعت چهارم",
                    sejdeMessage = "سجده دوم"
                )

            }
            9 -> {
                imageViewAnimatedChange(
                    newImage = R.drawable.alah,
                    rokaatMessage = "",
                    sejdeMessage =""
                )
            }
            else -> {
                return false
            }

        }
        return true
    }
    private fun getTime(): Long {
        return Calendar.getInstance().timeInMillis
    }
    override fun onDestroyView() {
        mSensorManager.unregisterListener(sensListener)
        super.onDestroyView()
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
