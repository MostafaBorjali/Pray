package com.borjali.mostafa.pray.ui.dashboard

import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.borjali.mostafa.pray.R
import kotlinx.android.synthetic.main.fragment_dashboard.*

import java.util.*


class DashboardFragment : Fragment() {
    private val waitTime: Long = 1000
    private var step = 0
    private var waiting_time: Long = 0
    private var isNear = false
    private lateinit var mSensorManager: SensorManager
    private var mSensor: Sensor? = null
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prepar()
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {

        })

        return root


    }

    private fun prepar() {

        mSensorManager = context?.getSystemService(SENSOR_SERVICE) as SensorManager

        Handler().postDelayed(
            {
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
                if (waiting_time < getTime()) isNear = true
            } else {
                if (isNear) {
                    isNear = false
                    setStep(++step)
                    waiting_time = getTime() + waitTime
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }

    private fun setStep(currentState: Int) : Boolean {

        //val animationIn = AnimationUtils.loadAnimation(context,R.anim.left_to_right_in)
       // val animationOut = AnimationUtils.loadAnimation(context,R.anim.left_to_right_out)
       // imgRocaat.inAnimation = animationIn
       // imgRocaat.outAnimation = animationOut

        when (currentState) {
            0 -> {
                imgRocaat.setImageResource(R.drawable.alah)
            }
            1 -> {
                imgRocaat.setImageResource(R.drawable.ic_1_1)
            }
            2 -> {
                imgRocaat.setImageResource(R.drawable.ic_1_2)
            }
            3 -> {
                imgRocaat.setImageResource(R.drawable.ic_2_1)
            }
            4 -> {
                imgRocaat.setImageResource(R.drawable.ic_2_2)
            }
            5 -> {
                imgRocaat.setImageResource(R.drawable.ic_3_1)
            }
            6 -> {
                imgRocaat.setImageResource(R.drawable.ic_3_2)
            }
            7 -> {
                imgRocaat.setImageResource(R.drawable.ic_4_1)
            }
            8 -> {
                imgRocaat.setImageResource(R.drawable.ic_4_2)
            }
            9 -> {
                imgRocaat.setImageResource(R.drawable.alah)
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

}
