package com.skgezhil.allinone

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.WindowManager
import android.widget.Toast
import com.sembozdemir.permissionskt.askPermissions
import com.sembozdemir.permissionskt.isPermissionGranted
import kotlinx.android.synthetic.main.activity_main.*
import java.security.Permission

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private val TAG = "tag"
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
            askPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) {

            }

        val actionbar = supportActionBar
        actionbar?.setBackgroundDrawable(resources.getDrawable(R.drawable.actionbar))
        sensors.setOnClickListener {
            var myIntent = Intent(baseContext, sensorActivity::class.java)
            startActivity(myIntent)
        }
        socialmedia.setOnClickListener {
            var myIntent = Intent(baseContext, SocialMedia::class.java)
            startActivity(myIntent)
        }
        shopping.setOnClickListener {
            var myIntent = Intent(baseContext, Shopping::class.java)
            startActivity(myIntent)
        }
        tickets.setOnClickListener {
            var myIntent = Intent(baseContext, Tickets::class.java)
            startActivity(myIntent)
        }
        food?.setOnClickListener {
            var myIntent = Intent(baseContext, Food::class.java)
            startActivity(myIntent)
        }
        education?.setOnClickListener {
            var myIntent = Intent(baseContext, Education::class.java)
            startActivity(myIntent)
        }
        tour?.setOnClickListener {
            var myIntent = Intent(baseContext, Tour::class.java)
            startActivity(myIntent)
        }
        info?.setOnClickListener {
            var myIntent = Intent(baseContext, Info::class.java)
            startActivity(myIntent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
