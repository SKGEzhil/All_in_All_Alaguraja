package com.skgezhil.allinone

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_food.*
import kotlinx.android.synthetic.main.activity_food.adView
import kotlinx.android.synthetic.main.activity_social_media.*

@Suppress("DEPRECATION")
class Food : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
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
            swiggy.setBackgroundResource(R.drawable.btbg_dark)
            zomoto.setBackgroundResource(R.drawable.btbg_dark)
            ubereats.setBackgroundResource(R.drawable.btbg_dark)
            dominos.setBackgroundResource(R.drawable.btbg_dark)
            pizzahut.setBackgroundResource(R.drawable.btbg_dark)
        }
        else {
            actionbar?.setBackgroundDrawable(resources.getDrawable(com.skgezhil.allinone.R.drawable.actionbar))
        }
        val swiggyl = "https://www.swiggy.com"
        val zomotol = "https:/zomoto.com"
        val ubereatsl = "https://www.ubereats.com"
        val dominosl = "https://www.dominos.com"
        val pizzahutl = "https://www.pizzahut.com"

        swiggy?.setOnClickListener(){
            val imprintIntent = Intent(this@Food, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",swiggyl)
            this.startActivity(imprintIntent)
        }
        zomoto?.setOnClickListener(){
            val imprintIntent = Intent(this@Food, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint", zomotol)
            this.startActivity(imprintIntent)
        }
        ubereats?.setOnClickListener(){
            val imprintIntent = Intent(this@Food, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",ubereatsl)
            this.startActivity(imprintIntent)
        }
        dominos?.setOnClickListener(){
            val imprintIntent = Intent(this@Food, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",dominosl)
            this.startActivity(imprintIntent)
        }
        pizzahut?.setOnClickListener(){
            val imprintIntent = Intent(this@Food, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",pizzahutl)
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
