package com.skgezhil.allinone


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_social_media.*
import kotlinx.android.synthetic.main.activity_social_media.adView


@Suppress("DEPRECATION")
class SocialMedia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_media)
        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder()
            .build()
        adView.loadAd(adRequest)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val actionbar = supportActionBar
        val Themesetting : SharedPreferences = getSharedPreferences("Themesetting", 0)
        val Themeeditor: SharedPreferences.Editor = Themesetting.edit()
        val isNightModeOn: Boolean = Themesetting?.getBoolean("NightMode", false)
        if (isNightModeOn){
            actionbar?.setBackgroundDrawable(resources.getDrawable(com.skgezhil.allinone.R.color.black))
            facebook.setBackgroundResource(R.drawable.btbg_dark)
            instagam.setBackgroundResource(R.drawable.btbg_dark)
            snapchat.setBackgroundResource(R.drawable.btbg_dark)
            linkedin.setBackgroundResource(R.drawable.btbg_dark)
            pinterest.setBackgroundResource(R.drawable.btbg_dark)
            twitter.setBackgroundResource(R.drawable.btbg_dark)
            reddit.setBackgroundResource(R.drawable.btbg_dark)
            youtube.setBackgroundResource(R.drawable.btbg_dark)
            zoom.setBackgroundResource(R.drawable.btbg_dark)
        }
        else {
            actionbar?.setBackgroundDrawable(resources.getDrawable(com.skgezhil.allinone.R.drawable.actionbar))
        }
        val facebookl = "http://www.facebook.com"
        val instagraml = "http://www.instagram.com"
        val snapchatl = "http://accounts.snapchat.com"
        val linkedinl = "https://www.linkedin.com"
        val pinterestl = "http://www.pinterest.com"
        val twitterl = "http://www.twitter.com"
        val youtubel = "https://www.m.youtube.com"
        val redditl = "https://www.reddit.com"
        var zooml = "https://zoom.us"
        facebook?.setOnClickListener(){
            val imprintIntent = Intent(this@SocialMedia, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint", facebookl)
            this.startActivity(imprintIntent)
        }
        instagam?.setOnClickListener(){
            val imprintIntent = Intent(this@SocialMedia, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint", instagraml)
            this.startActivity(imprintIntent)
        }
        snapchat?.setOnClickListener(){
            val imprintIntent = Intent(this@SocialMedia, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",snapchatl)
            this.startActivity(imprintIntent)
        }
        linkedin?.setOnClickListener(){
            val imprintIntent = Intent(this@SocialMedia, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",linkedinl)
            this.startActivity(imprintIntent)
        }
        pinterest?.setOnClickListener(){
            val imprintIntent = Intent(this@SocialMedia, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",pinterestl)
            this.startActivity(imprintIntent)
        }
        twitter?.setOnClickListener(){
            val imprintIntent = Intent(this@SocialMedia, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",twitterl)
            this.startActivity(imprintIntent)
        }
        youtube?.setOnClickListener(){
            val imprintIntent = Intent(this@SocialMedia, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",youtubel)
            this.startActivity(imprintIntent)
        }
        reddit?.setOnClickListener(){
            val imprintIntent = Intent(this@SocialMedia, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",redditl)
            this.startActivity(imprintIntent)
        }
        zoom?.setOnClickListener(){
            val imprintIntent = Intent(this@SocialMedia, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",zooml)
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


