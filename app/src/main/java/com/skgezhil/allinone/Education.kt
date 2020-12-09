package com.skgezhil.allinone

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_education.*
import kotlinx.android.synthetic.main.activity_education.adView
import kotlinx.android.synthetic.main.activity_social_media.*

@Suppress("DEPRECATION")
class Education : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education)
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
            brainly.setBackgroundResource(R.drawable.btbg_dark)
            instasolv.setBackgroundResource(R.drawable.btbg_dark)
            quora.setBackgroundResource(R.drawable.btbg_dark)
        }
        else {
            actionbar?.setBackgroundDrawable(resources.getDrawable(com.skgezhil.allinone.R.drawable.actionbar))
        }
        val brainlyl = "https://www.brainly.in"
        val quoral = "https://www.quora.com"
        val instasolvl = "https://www.instasolv.com"
        brainly?.setOnClickListener(){
            val imprintIntent = Intent(this@Education, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",brainlyl)
            this.startActivity(imprintIntent)
        }
        quora?.setOnClickListener(){
            val imprintIntent = Intent(this@Education, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint", quoral)
            this.startActivity(imprintIntent)
        }
        instasolv?.setOnClickListener(){
            val imprintIntent = Intent(this@Education, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",instasolvl)
            this.startActivity(imprintIntent)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
