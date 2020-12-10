package com.skgezhil.allinone

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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
        setContentView(com.skgezhil.allinone.R.layout.activity_main)
        val actionbar = supportActionBar
        val Themesetting : SharedPreferences = getSharedPreferences("Themesetting", 0)
        val Themeeditor: SharedPreferences.Editor = Themesetting.edit()
        val isNightModeOn: Boolean = Themesetting?.getBoolean("NightMode", false)
        if (isNightModeOn){
            actionbar?.setBackgroundDrawable(resources.getDrawable(com.skgezhil.allinone.R.color.black))
            sensors.setBackgroundResource(R.drawable.btbg_dark)
            socialmedia.setBackgroundResource(R.drawable.btbg_dark)
            shopping.setBackgroundResource(R.drawable.btbg_dark)
            tickets.setBackgroundResource(R.drawable.btbg_dark)
            food.setBackgroundResource(R.drawable.btbg_dark)
            education.setBackgroundResource(R.drawable.btbg_dark)
            info.setBackgroundResource(R.drawable.btbg_dark)
            medical.setBackgroundResource(R.drawable.btbg_dark)
            tour.setBackgroundResource(R.drawable.btbg_dark)
        }
        else {
            actionbar?.setBackgroundDrawable(resources.getDrawable(com.skgezhil.allinone.R.drawable.actionbar))
        }
        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder()

            .build()
        adView.loadAd(adRequest)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
            askPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) {

            }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(com.skgezhil.allinone.R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            com.skgezhil.allinone.R.id.theme_menu -> {
                val actionbarnight = supportActionBar
                val Themesetting : SharedPreferences = getSharedPreferences("Themesetting", 0)
                val Themeeditor: SharedPreferences.Editor = Themesetting.edit()
                val isNightModeOn: Boolean = Themesetting?.getBoolean("NightMode", false)
                Themesetting != null
                if (isNightModeOn) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    Themeeditor?.putBoolean("NightMode", false)
                    Themeeditor?.apply()
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    Themeeditor?.putBoolean("NightMode", true)
                    Themeeditor?.apply()

                }
                Toast.makeText(this, "Theme Activated", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}
