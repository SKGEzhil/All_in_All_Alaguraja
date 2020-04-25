package com.skgezhil.allinone

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_shopping.*
import kotlinx.android.synthetic.main.activity_tickets.*

@Suppress("DEPRECATION")
class Tickets : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val actionbar = supportActionBar
        actionbar?.setBackgroundDrawable(resources.getDrawable(R.drawable.actionbar))
        val raill = "https://www.railyatri.in/train-ticket"
        val flightl = "https://www.google.com/flights?hl=en#flt=/m/019fbp..2020-05-03*./m/019fbp.2020-05-07;c:INR;e:1;ls:1w;sd:0;t:h"
        val moviel = "https://in.bookmyshow.com/"
        val redbusl = "https://m.redbus.in"
        rail?.setOnClickListener(){
            val imprintIntent = Intent(this@Tickets, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",raill)
            this.startActivity(imprintIntent)
        }
        flight?.setOnClickListener(){
            val imprintIntent = Intent(this@Tickets, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint", flightl)
            this.startActivity(imprintIntent)
        }
        movie?.setOnClickListener(){
            val imprintIntent = Intent(this@Tickets, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",moviel)
            this.startActivity(imprintIntent)
        }
        redbus?.setOnClickListener(){
            val imprintIntent = Intent(this@Tickets, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",redbusl)
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
