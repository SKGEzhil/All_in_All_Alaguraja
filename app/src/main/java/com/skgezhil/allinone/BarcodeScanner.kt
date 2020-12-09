package com.skgezhil.allinone

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class BarcodeScanner : AppCompatActivity() {
    private var btn: Button? = null
    private var myClipboard: ClipboardManager? = null
    private var myClip: ClipData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode_scanner)
        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder()

            .build()
        adView.loadAd(adRequest)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // Here, thisActivity is the current activity


        val actionbar = supportActionBar
        myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
        tvresult = findViewById(R.id.tvresult) as TextView
        var search = findViewById(R.id.search) as Button
        var copytext = findViewById(R.id.copy) as Button
        copytext.setOnClickListener {
         myClip = ClipData.newPlainText("text", tvresult!!.text);
         myClipboard?.setPrimaryClip(myClip);
         Toast.makeText(this, "Text Copied", Toast.LENGTH_SHORT).show();
        }
        btn = findViewById(R.id.btn) as Button

        val Themesetting : SharedPreferences = getSharedPreferences("Themesetting", 0)
        val Themeeditor: SharedPreferences.Editor = Themesetting.edit()
        val isNightModeOn: Boolean = Themesetting?.getBoolean("NightMode", false)
        if (isNightModeOn){
            actionbar?.setBackgroundDrawable(resources.getDrawable(com.skgezhil.allinone.R.color.black))
            btn?.setBackgroundResource(R.drawable.btbg_dark)
            copytext.setBackgroundResource(R.drawable.btbg_dark)
            search.setBackgroundResource(R.drawable.btbg_dark)
        }
        else {
            actionbar?.setBackgroundDrawable(resources.getDrawable(com.skgezhil.allinone.R.drawable.actionbar))
        }
        btn!!.setOnClickListener {
            val intent = Intent(this@BarcodeScanner, ScanActivity::class.java)
            startActivity(intent)
        }
        search?.setOnClickListener(){
            val imprintIntent = Intent(this@BarcodeScanner, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint", tvresult!!.text )
            this.startActivity(imprintIntent)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    companion object {

        var tvresult: TextView? = null
    }
}
