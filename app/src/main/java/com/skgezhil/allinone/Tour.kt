package com.skgezhil.allinone

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_shopping.*
import kotlinx.android.synthetic.main.activity_tickets.*
import kotlinx.android.synthetic.main.activity_tour.*

@Suppress("DEPRECATION")
class Tour : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tour)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val actionbar = supportActionBar
        actionbar?.setBackgroundDrawable(resources.getDrawable(R.drawable.actionbar))
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
