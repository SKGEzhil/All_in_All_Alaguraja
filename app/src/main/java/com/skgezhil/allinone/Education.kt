package com.skgezhil.allinone

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_education.*
import kotlinx.android.synthetic.main.activity_shopping.*
import kotlinx.android.synthetic.main.activity_tickets.*

@Suppress("DEPRECATION")
class Education : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val actionbar = supportActionBar
        actionbar?.setBackgroundDrawable(resources.getDrawable(R.drawable.actionbar))
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
