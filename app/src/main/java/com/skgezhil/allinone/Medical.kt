package com.skgezhil.allinone

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_medical.*
import kotlinx.android.synthetic.main.activity_tour.adView

@Suppress("DEPRECATION")
class Medical : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical)
        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder()
            .build()
        adView.loadAd(adRequest)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val actionbar = supportActionBar
        actionbar?.setBackgroundDrawable(resources.getDrawable(R.drawable.actionbar))
        val practol = "https://www.practo.com/consult"
        val doconlinel = "https://www.doconline.com/"
        val icliniql = "https://www.icliniq.com/en_IN/"
        practo?.setOnClickListener(){
            val imprintIntent = Intent(this@Medical, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",practol)
            this.startActivity(imprintIntent)
        }
        doconline?.setOnClickListener(){
            val imprintIntent = Intent(this@Medical, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",doconlinel)
            this.startActivity(imprintIntent)
        }
        icliniq?.setOnClickListener(){
            val imprintIntent = Intent(this@Medical, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",icliniql)
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
