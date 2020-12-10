package com.skgezhil.allinone

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_social_media.*
import kotlinx.android.synthetic.main.activity_tickets.*
import kotlinx.android.synthetic.main.activity_tickets.adView

@Suppress("DEPRECATION")
class Tickets : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)
        MobileAds.initialize(this) {
            val adRequest = AdRequest.Builder()
                .build()
            adView.loadAd(adRequest)
            adView.adListener = object : AdListener() {
                override fun onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                    Toast.makeText(applicationContext, "ad loaded", Toast.LENGTH_LONG)
                }

                override fun onAdFailedToLoad(errorCode: Int) {
                    // Code to be executed when an ad request fails.
                    Toast.makeText(applicationContext, "ad failed", Toast.LENGTH_LONG)
                }

                override fun onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                }

                override fun onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                }

                override fun onAdLeftApplication() {
                    // Code to be executed when the user has left the app.
                }

                override fun onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                }
            }
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            val actionbar = supportActionBar
            val Themesetting: SharedPreferences = getSharedPreferences("Themesetting", 0)
            val Themeeditor: SharedPreferences.Editor = Themesetting.edit()
            val isNightModeOn: Boolean = Themesetting?.getBoolean("NightMode", false)
            if (isNightModeOn) {
                actionbar?.setBackgroundDrawable(resources.getDrawable(com.skgezhil.allinone.R.color.black))
                rail.setBackgroundResource(R.drawable.btbg_dark)
                flight.setBackgroundResource(R.drawable.btbg_dark)
                movie.setBackgroundResource(R.drawable.btbg_dark)
                redbus.setBackgroundResource(R.drawable.btbg_dark)
            } else {
                actionbar?.setBackgroundDrawable(resources.getDrawable(com.skgezhil.allinone.R.drawable.actionbar))
            }
            val raill = "https://www.railyatri.in/train-ticket"
            val flightl =
                "https://www.google.com/flights?hl=en#flt=/m/019fbp..2020-05-03*./m/019fbp.2020-05-07;c:INR;e:1;ls:1w;sd:0;t:h"
            val moviel = "https://in.bookmyshow.com/"
            val redbusl = "https://m.redbus.in"
            rail?.setOnClickListener() {
                val imprintIntent = Intent(this@Tickets, Webviewer::class.java)
                imprintIntent.putExtra("webivewImprint", raill)
                this.startActivity(imprintIntent)
            }
            flight?.setOnClickListener() {
                val imprintIntent = Intent(this@Tickets, Webviewer::class.java)
                imprintIntent.putExtra("webivewImprint", flightl)
                this.startActivity(imprintIntent)
            }
            movie?.setOnClickListener() {
                val imprintIntent = Intent(this@Tickets, Webviewer::class.java)
                imprintIntent.putExtra("webivewImprint", moviel)
                this.startActivity(imprintIntent)
            }
            redbus?.setOnClickListener() {
                val imprintIntent = Intent(this@Tickets, Webviewer::class.java)
                imprintIntent.putExtra("webivewImprint", redbusl)
                this.startActivity(imprintIntent)
            }
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        val myintent = Intent(baseContext, MainActivity::class.java)
        startActivity(myintent)
        finish()
    }
}