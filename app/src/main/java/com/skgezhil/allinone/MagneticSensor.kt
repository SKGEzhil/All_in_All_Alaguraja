package com.skgezhil.allinone

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_magnetic_sensor.*

@Suppress("DEPRECATION")
class MagneticSensor : AppCompatActivity() {
    lateinit var tvText: TextView
    lateinit var sensorManager: SensorManager
    lateinit var sensors: List<Sensor>
    lateinit var sensorLight: Sensor

    var listenerLight: SensorEventListener = object : SensorEventListener{
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            //
        }

        override fun onSensorChanged(event: SensorEvent) {
            tvText.text = event.values[0].toString()
            if (event.values[0] > 100)
                detection.setText("METAL DETECTED")
            else
                detection.setText("METAL NOT DETECTED")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val actionbar = supportActionBar
        actionbar?.setBackgroundDrawable(resources.getDrawable(R.drawable.actionbar))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_magnetic_sensor)
        tvText = findViewById<View>(R.id.value) as TextView
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    }

    override fun onStart() {
        super.onStart()
        sensorManager.registerListener(listenerLight, sensorLight, SensorManager.SENSOR_DELAY_NORMAL)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
