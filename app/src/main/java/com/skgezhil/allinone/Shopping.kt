package com.skgezhil.allinone

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_shopping.*
import kotlinx.android.synthetic.main.activity_shopping.adView
import kotlinx.android.synthetic.main.activity_social_media.*

@Suppress("DEPRECATION")
class Shopping : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
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
            amazon.setBackgroundResource(R.drawable.btbg_dark)
            flipkart.setBackgroundResource(R.drawable.btbg_dark)
            snapcdeal.setBackgroundResource(R.drawable.btbg_dark)
            justdial.setBackgroundResource(R.drawable.btbg_dark)
            aliexpress.setBackgroundResource(R.drawable.btbg_dark)
            banggood.setBackgroundResource(R.drawable.btbg_dark)
        }
        else {
            actionbar?.setBackgroundDrawable(resources.getDrawable(com.skgezhil.allinone.R.drawable.actionbar))
        }
        val amazonl = "https://www.amazon.in/"
        val flipkartl = "https://www.flipkart.com/"
        val snapdeall = "https://www.snapdeal.com/"
        val justdiall = "https://www.justdial.com/"
        val aliexpressl = "https://www.aliexpress.com/?src=google&albch=fbrnd&acnt=304-410-9721&isdl=y&aff_short_key=UneMJZVf&albcp=2038900985&albag=71795909413&slnk=&trgt=kwd-14802285088&plac=&crea=399370967278&netw=g&device=c&mtctp=e&memo1=&albbt=Google_7_fbrnd&aff_platform=google&albagn=888888&gclid=CjwKCAjwp-X0BRAFEiwAheRuiy75-PoRWRuy39qSvQbFP8gnuoC6n9lVcoGjXBKVTODrFexnW_g9RBoCSEEQAvD_BwE"
        val banggoodl = "https://www.banggood.in/"
        amazon.setOnClickListener(){
            val imprintIntent = Intent(this@Shopping, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint", amazonl)
            this.startActivity(imprintIntent)
        }
        flipkart.setOnClickListener(){
            val imprintIntent = Intent(this@Shopping, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint", flipkartl)
            this.startActivity(imprintIntent)
        }
        snapcdeal.setOnClickListener(){
            val imprintIntent = Intent(this@Shopping, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",snapdeall)
            this.startActivity(imprintIntent)
        }
        justdial.setOnClickListener(){
            val imprintIntent = Intent(this@Shopping, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",justdiall)
            this.startActivity(imprintIntent)
        }
        aliexpress.setOnClickListener(){
            val imprintIntent = Intent(this@Shopping, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",aliexpressl)
            this.startActivity(imprintIntent)
        }
        banggood.setOnClickListener(){
            val imprintIntent = Intent(this@Shopping, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",banggoodl)
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
