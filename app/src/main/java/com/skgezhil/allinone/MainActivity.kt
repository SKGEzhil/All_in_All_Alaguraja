package com.skgezhil.allinone

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.sembozdemir.permissionskt.askPermissions
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private val TAG = "tag"
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder()

            .build()
        adView.loadAd(adRequest)
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
        medical?.setOnClickListener {
            var myIntent = Intent(baseContext, Medical::class.java)
            startActivity(myIntent)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
