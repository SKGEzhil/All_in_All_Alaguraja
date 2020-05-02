package com.skgezhil.allinone

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_magnetic_sensor.*

@Suppress("DEPRECATION")
class MagneticSensor : AppCompatActivity() {
    lateinit var tvText: TextView
    lateinit var sensorManager: SensorManager
    lateinit var sensors: List<Sensor>
     var sensorLight: Sensor ?= null

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
        try {
            sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        }
        catch (e: IllegalStateException){
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
            alert.setTitle("Cannot open Metal Detector")
            // show alert dialog
            alert.show()
        }
        catch (e: NullPointerException){
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
            alert.setTitle("Cannot open Metal Detector")
            // show alert dialog
            alert.show()
        }

    }

    override fun onStart() {
        super.onStart()
        try {
            sensorManager.registerListener(listenerLight, sensorLight, SensorManager.SENSOR_DELAY_NORMAL)
        }
        catch (e: IllegalStateException){
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
            alert.setTitle("Cannot open Metal Detector")
            // show alert dialog
            alert.show()
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
            alert.setTitle("Cannot open Metal Detector")
            // show alert dialog
            alert.show()
        }
        catch (e: NullPointerException){
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
            alert.setTitle("Cannot open Metal Detector")
            // show alert dialog
            alert.show()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
