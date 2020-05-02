package com.skgezhil.allinone

import android.content.DialogInterface
import android.content.Intent
import android.hardware.Sensor
import android.hardware.Sensor.TYPE_ACCELEROMETER
import android.hardware.Sensor.TYPE_MAGNETIC_FIELD
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.SensorManager.SENSOR_DELAY_GAME
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.RotateAnimation
import android.widget.ImageView
import java.lang.Math.toDegrees


@Suppress("DEPRECATION")
class Compass : AppCompatActivity(), SensorEventListener {
    lateinit var sensorManager: SensorManager
    lateinit var image: ImageView
    lateinit var accelerometer: Sensor
    lateinit var magnetometer: Sensor

    var currentDegree = 0.0f
    var lastAccelerometer = FloatArray(3)
    var lastMagnetometer = FloatArray(3)
    var lastAccelerometerSet = false
    var lastMagnetometerSet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compass)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val actionbar = supportActionBar
        actionbar?.setBackgroundDrawable(resources.getDrawable(R.drawable.actionbar))
        image = findViewById(R.id.compass) as ImageView
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(TYPE_ACCELEROMETER)
        try {
            magnetometer = sensorManager.getDefaultSensor(TYPE_MAGNETIC_FIELD)
        }
        catch (e: IllegalStateException){
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)
            var myIntent = Intent(baseContext, sensorActivity::class.java)
            // set message of alert dialog
            dialogBuilder.setMessage("This function is incompatible with your device")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("ok", DialogInterface.OnClickListener {
                        dialog, id -> finish()
                })

            // create dialog box
            val alert = dialogBuilder.create()
            alert.setIcon(R.drawable.icofull)
            // set title for alert dialog box
            alert.setTitle("Cannot open compass")
            // show alert dialog
            alert.show()
        }
    }

    override fun onResume() {
        super.onResume()

        sensorManager.registerListener(this, accelerometer, SENSOR_DELAY_GAME)
        try {
            sensorManager.registerListener(this, magnetometer, SENSOR_DELAY_GAME)
        }
        catch (e: UninitializedPropertyAccessException){
            val dialogBuilder = AlertDialog.Builder(this)
            var myIntent = Intent(baseContext, sensorActivity::class.java)
            // set message of alert dialog
            dialogBuilder.setMessage("This function is incompatible with your device")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("ok", DialogInterface.OnClickListener {
                        dialog, id -> finish()
                })

            // create dialog box
            val alert = dialogBuilder.create()
            alert.setIcon(R.drawable.icofull)
            // set title for alert dialog box
            alert.setTitle("Cannot open compass")
            // show alert dialog
            alert.show()
        }

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this, accelerometer)
        try {
            sensorManager.unregisterListener(this, magnetometer)
        }
        catch (e: UninitializedPropertyAccessException){
            val dialogBuilder = AlertDialog.Builder(this)
            var myIntent = Intent(baseContext, sensorActivity::class.java)
            // set message of alert dialog
            dialogBuilder.setMessage("This function is incompatible with your device")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("ok", DialogInterface.OnClickListener {
                        dialog, id -> finish()
                })

            // create dialog box
            val alert = dialogBuilder.create()
            alert.setIcon(R.drawable.icofull)
            // set title for alert dialog box
            alert.setTitle("Cannot open compass")
            // show alert dialog
            alert.show()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor === accelerometer) {
            lowPass(event.values, lastAccelerometer)
            lastAccelerometerSet = true
        } else if (event.sensor === magnetometer) {
            lowPass(event.values, lastMagnetometer)
            lastMagnetometerSet = true
        }

        if (lastAccelerometerSet && lastMagnetometerSet) {
            val r = FloatArray(9)
            if (SensorManager.getRotationMatrix(r, null, lastAccelerometer, lastMagnetometer)) {
                val orientation = FloatArray(3)
                SensorManager.getOrientation(r, orientation)
                val degree = (toDegrees(orientation[0].toDouble()) + 360).toFloat() % 360

                val rotateAnimation = RotateAnimation(
                    currentDegree,
                    -degree,
                    RELATIVE_TO_SELF, 0.5f,
                    RELATIVE_TO_SELF, 0.5f)
                rotateAnimation.duration = 1000
                rotateAnimation.fillAfter = true

                image.startAnimation(rotateAnimation)
                currentDegree = -degree
            }
        }
    }

    fun lowPass(input: FloatArray, output: FloatArray) {
        val alpha = 0.05f

        for (i in input.indices) {
            output[i] = output[i] + alpha * (input[i] - output[i])
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
