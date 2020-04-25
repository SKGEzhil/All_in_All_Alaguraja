package com.skgezhil.allinone

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.sembozdemir.permissionskt.askPermissions
import kotlinx.android.synthetic.main.activity_compass.*
import kotlinx.android.synthetic.main.activity_sensor.*

@Suppress("DEPRECATION")
class sensorActivity : AppCompatActivity() {
private val  MY_PERMISSIONS_REQUEST_CAMERA = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val actionbar = supportActionBar
        actionbar?.setBackgroundDrawable(resources.getDrawable(R.drawable.actionbar))
        val labnoll = "https://www.labnol.org/reverse/"

        light?.setOnClickListener {
            var myIntent = Intent(baseContext, LightSensor::class.java)
            startActivity(myIntent)
        }
        magnet?.setOnClickListener {
            var myIntent = Intent(baseContext, MagneticSensor::class.java)
            startActivity(myIntent)
        }
        accelerometer?.setOnClickListener {
            var myIntent = Intent(baseContext, Accelerometer::class.java)
            startActivity(myIntent)
        }
        compassbtn?.setOnClickListener {
            var myIntent = Intent(baseContext, Compass::class.java)
            startActivity(myIntent)
        }
        pedometer?.setOnClickListener {
            var myIntent = Intent(baseContext, Pedometer::class.java)
            startActivity(myIntent)
        }
        barcodescanner?.setOnClickListener {
            var myIntent = Intent(baseContext, BarcodeScanner::class.java)
            startActivity(myIntent)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val myintent = Intent(baseContext,MainActivity::class.java)
        startActivity(myintent)
        finish()
    }
}
