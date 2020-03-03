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
        Handler().postDelayed({
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            mSensorManager.registerListener(
                sensListener,
                mSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
            step = 0
            setStep(step)
        }, 2000)
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

    private fun setStep(currentState: Int) {
        when (currentState) {
            0 -> {
                imgRocaat.setImageResource(R.drawable.alah)
            }
            1 -> {
                Toast.makeText(context, "Rockaat1 ----- sejdeh 1", Toast.LENGTH_LONG).show()
            }
            2 -> {
                Toast.makeText(context, "Rockaat1 ----- sejdeh 2222", Toast.LENGTH_LONG).show()
            }
            3 -> {
                Toast.makeText(context, "Rockaat222 ----- sejdeh 1", Toast.LENGTH_LONG).show()
            }
            4 -> {
                Toast.makeText(context, "Rockaat222 ----- sejdeh 222", Toast.LENGTH_LONG).show()
            }
            5 -> {
                Toast.makeText(context, "Rockaat333 ----- sejdeh 1", Toast.LENGTH_LONG).show()
            }
            6 -> {
                Toast.makeText(context, "Rockaat3333 ----- sejdeh 2222", Toast.LENGTH_LONG).show()
            }
            7 -> {
                Toast.makeText(context, "Rockaat444 ----- sejdeh 1", Toast.LENGTH_LONG).show()
            }
            8 -> {
                Toast.makeText(context, "Rockaat444 ----- sejdeh 22222", Toast.LENGTH_LONG).show()
            }
            else -> {
            }
        }
    }

    private fun getTime(): Long {
        return Calendar.getInstance().timeInMillis
    }

}
