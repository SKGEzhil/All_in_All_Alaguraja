package com.skgezhil.allinone

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_social_media.*
import kotlinx.android.synthetic.main.activity_tour.*
import kotlinx.android.synthetic.main.activity_tour.adView

@Suppress("DEPRECATION")
class Tour : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tour)
        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder()
            .build()
        adView.loadAd(adRequest)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val actionbar = supportActionBar
        val Themesetting : SharedPreferences = getSharedPreferences("Themesetting", 0)
        val Themeeditor: SharedPreferences.Editor = Themesetting.edit()
        val isNightModeOn: Boolean = Themesetting?.getBoolean("NightMode", false)
        if (isNightModeOn){
            actionbar?.setBackgroundDrawable(resources.getDrawable(com.skgezhil.allinone.R.color.black))
            goibbo.setBackgroundResource(R.drawable.btbg_dark)
            makemytrip.setBackgroundResource(R.drawable.btbg_dark)
            tripadvisor.setBackgroundResource(R.drawable.btbg_dark)
        }
        else {
            actionbar?.setBackgroundDrawable(resources.getDrawable(com.skgezhil.allinone.R.drawable.actionbar))
        }
        val goibbol = "https://www.goibbo.com"
        val makemytripl = "https://www.makemytrip.com/"
        val tripadvisorl = "https://www.tripadvisor.in"
        goibbo?.setOnClickListener(){
            val imprintIntent = Intent(this@Tour, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",goibbol)
            this.startActivity(imprintIntent)
        }
        makemytrip?.setOnClickListener(){
            val imprintIntent = Intent(this@Tour, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",makemytripl)
            this.startActivity(imprintIntent)
        }
        tripadvisor?.setOnClickListener(){
            val imprintIntent = Intent(this@Tour, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",tripadvisorl)
            this.startActivity(imprintIntent)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val myintent = Intent(baseContext,MainActivity::class.java)
        startActivity(myintent)
        finish()
    }
}
