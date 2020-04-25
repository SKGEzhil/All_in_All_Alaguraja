package com.skgezhil.allinone

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.sembozdemir.permissionskt.askPermissions
import com.sembozdemir.permissionskt.isPermissionGranted
import com.skgezhil.allinone.BarcodeScanner.Companion as ComSkgezhilAllinoneBarcodeScanner
import android.Manifest

@Suppress("DEPRECATION")
class BarcodeScanner : AppCompatActivity() {
    private var btn: Button? = null
    private var myClipboard: ClipboardManager? = null
    private var myClip: ClipData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode_scanner)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // Here, thisActivity is the current activity


        val actionbar = supportActionBar
        actionbar?.setBackgroundDrawable(resources.getDrawable(R.drawable.actionbar))
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
