package com.skgezhil.allinone

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_food.*

@Suppress("DEPRECATION")
class Food : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val actionbar = supportActionBar
        actionbar?.setBackgroundDrawable(resources.getDrawable(R.drawable.actionbar))
        val swiggyl = "https://www.swiggy.com"
        val zomotol = "https:/zomoto.com"
        val ubereatsl = "https://www.ubereats.com"
        val dominosl = "https://www.dominos.com"
        val pizzahutl = "https://www.pizzahut.com"

        swiggy?.setOnClickListener(){
            val imprintIntent = Intent(this@Food, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",swiggyl)
            this.startActivity(imprintIntent)
        }
        zomoto?.setOnClickListener(){
            val imprintIntent = Intent(this@Food, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint", zomotol)
            this.startActivity(imprintIntent)
        }
        ubereats?.setOnClickListener(){
            val imprintIntent = Intent(this@Food, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",ubereatsl)
            this.startActivity(imprintIntent)
        }
        dominos?.setOnClickListener(){
            val imprintIntent = Intent(this@Food, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",dominosl)
            this.startActivity(imprintIntent)
        }
        pizzahut?.setOnClickListener(){
            val imprintIntent = Intent(this@Food, Webviewer::class.java)
            imprintIntent.putExtra("webivewImprint",pizzahutl)
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
