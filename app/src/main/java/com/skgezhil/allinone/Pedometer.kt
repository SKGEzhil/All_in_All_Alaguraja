package com.skgezhil.allinone

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.codility.pedometer.listener.StepListener
import com.codility.pedometer.utils.StepDetector
import kotlinx.android.synthetic.main.activity_pedometer.*
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Created by Govind on 05/25/2018.
 */
@Suppress("DEPRECATION")
class Pedometer : AppCompatActivity(), SensorEventListener, StepListener {
    private var simpleStepDetector: StepDetector? = null
    private var sensorManager: SensorManager? = null
    private val TEXT_NUM_STEPS = "STEPS  "
    private val TEXT_CALORIE = "CALORIES  "
    private val TEXT_DISTANCE = "DISTANCE  "
    private var numSteps: Double = 0.0
    var calorie : Double = 0.00
    var kilometer : Double = 0.000
    val number2digits:Double = Math.round(kilometer * 100.0) / 100.0
    val solution:Double = Math.round(number2digits * 10.0) / 10.0
    fun main(args: Array<String>) {


    }
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector!!.updateAccelerometer(event.timestamp, event.values[0], event.values[1], event.values[2])
        }
    }

    override fun step(timeNs: Long) {
        numSteps++
        value.text = TEXT_NUM_STEPS.plus(numSteps)
        calorie = (numSteps * 4)/100
        kilometer = numSteps/1400
        val df = DecimalFormat("#.###")
        df.roundingMode = RoundingMode.CEILING
        calories.text = TEXT_CALORIE.plus(calorie)
        distance.text = TEXT_DISTANCE.plus(df.format(kilometer))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedometer)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val actionbar = supportActionBar
        actionbar?.setBackgroundDrawable(resources.getDrawable(R.drawable.actionbar))
        // Get an instance of the SensorManager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        simpleStepDetector = StepDetector()
        simpleStepDetector!!.registerListener(this)

        btnstart.setOnClickListener(View.OnClickListener {
            numSteps = 0.0
            sensorManager!!.registerListener(this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST)
        })

        btnstop.setOnClickListener(View.OnClickListener {
            sensorManager!!.unregisterListener(this)
            value.text = "STEPS 0"
            calories.text = "0"
            distance.text="0"
        })
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}