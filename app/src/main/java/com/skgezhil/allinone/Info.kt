package com.skgezhil.allinone

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_info.*

@Suppress("DEPRECATION")
class Info : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder()
            .build()
        adView.loadAd(adRequest)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val actionbar = supportActionBar
        actionbar?.setBackgroundDrawable(resources.getDrawable(R.drawable.actionbar))
        val playstorel = "https://play.google.com/store/apps/details?id=com.skgezhil.allinone&hl=en"
        val youtubesubl = "https://www.youtube.com/channel/UCIr-OiT1ph9IeCMS_dOB0JA"
        val blogl = "https://skgezhil.blogspot.com"
        var instagramtxtl = "https://www.instagram.com/skgezhil2005/"
        playstore?.setOnClickListener(){
            val imprintIntent = Intent(this@Info, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",playstorel)
            this.startActivity(imprintIntent)
        }
        blog?.setOnClickListener(){
            val imprintIntent = Intent(this@Info, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",blogl)
            this.startActivity(imprintIntent)
        }
        youtube_sub?.setOnClickListener(){
            val imprintIntent = Intent(this@Info, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",youtubesubl)
            this.startActivity(imprintIntent)
        }
        instagram_text?.setOnClickListener(){
            val imprintIntent = Intent(this@Info, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",instagramtxtl)
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
