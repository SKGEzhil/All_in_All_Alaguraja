package com.skgezhil.allinone

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import kotlinx.android.synthetic.main.activity_sensor.*

@Suppress("DEPRECATION")
class sensorActivity : AppCompatActivity(), RewardedVideoAdListener {

    // cached references

    private val  MY_PERMISSIONS_REQUEST_CAMERA = 123
    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var mRewardedVideoAd: RewardedVideoAd
    var rewardv : Int ?= null
    var interstitialv : Int ?= null

    // functions

    private fun loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-7716695643466392/6622576185",
            AdRequest
                .Builder()
                .build()
        )
    }

    private fun loadinterstitialads(){
        mInterstitialAd.loadAd(AdRequest
            .Builder()
            .build()
        )
    }

    //override modifiers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        //banner ads

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder()
            .build()
        adView.loadAd(adRequest)

        //reward ads

        MobileAds.initialize(this, "ca-app-pub-7716695643466392/6622576185")
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        mRewardedVideoAd.rewardedVideoAdListener = this
        loadRewardedVideoAd()

        //interstitial ads

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-7716695643466392/5373428915"
        loadinterstitialads()

        // full screen and actionbar

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val actionbar = supportActionBar
        val Themesetting : SharedPreferences = getSharedPreferences("Themesetting", 0)
        val Themeeditor: SharedPreferences.Editor = Themesetting.edit()
        val isNightModeOn: Boolean = Themesetting?.getBoolean("NightMode", false)
        if (isNightModeOn){
            actionbar?.setBackgroundDrawable(resources.getDrawable(com.skgezhil.allinone.R.color.black))
            compassbtn.setBackgroundResource(R.drawable.btbg_dark)
            pedometer.setBackgroundResource(R.drawable.btbg_dark)
            barcodescanner.setBackgroundResource(R.drawable.btbg_dark)
        }
        else {
            actionbar?.setBackgroundDrawable(resources.getDrawable(com.skgezhil.allinone.R.drawable.actionbar))
        }

        //button click listeners

        compassbtn?.setOnClickListener {
            Toast.makeText(applicationContext,"Watch ad to continue...",Toast.LENGTH_LONG).show()
            rewardv = 1
            if (mRewardedVideoAd.isLoaded) {
                mRewardedVideoAd.show()
            }
            else{
                Toast.makeText(applicationContext,"Ad not loaded",Toast.LENGTH_LONG).show()
                var myIntent = Intent(baseContext, Compass ::class.java)
                startActivity(myIntent)
            }
        }
        pedometer?.setOnClickListener {
            var myIntent = Intent(baseContext, Pedometer::class.java)
            startActivity(myIntent)
        }
        barcodescanner?.setOnClickListener {
            Toast.makeText(applicationContext,"Watch ad to continue...",Toast.LENGTH_LONG).show()
            interstitialv = 1
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                Toast.makeText(applicationContext,"Ad not loaded",Toast.LENGTH_LONG).show()
                var myIntent = Intent(baseContext, BarcodeScanner::class.java)
                startActivity(myIntent)
            }
        }

        adView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(errorCode : Int) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        }

        //interstitial ad listeners

        mInterstitialAd.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
                loadinterstitialads()
                if (interstitialv == 1) {
                    var myIntent = Intent(baseContext, BarcodeScanner::class.java)
                    startActivity(myIntent)
                    interstitialv = 0
                }
            }

            override fun onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            override fun onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                loadinterstitialads()
                if (interstitialv == 1) {
                    var myIntent = Intent(baseContext, BarcodeScanner::class.java)
                    startActivity(myIntent)
                    interstitialv = 0
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val myintent = Intent(baseContext,MainActivity::class.java)
        startActivity(myintent)
        finish()
    }

    //reward ad listeners

    override fun onRewarded(reward: RewardItem) {
        // Reward the user.
    }

    override fun onRewardedVideoAdLeftApplication() {
    }

    override fun onRewardedVideoAdClosed() {
        loadRewardedVideoAd()
        if (rewardv == 1) {
            var myIntent = Intent(baseContext, Compass::class.java)
            startActivity(myIntent)
            rewardv = 0
        }
    }

    override fun onRewardedVideoAdFailedToLoad(errorCode: Int) {
        loadRewardedVideoAd()
        if (rewardv == 1) {
            var myIntent = Intent(baseContext, Compass::class.java)
            startActivity(myIntent)
            rewardv = 0
        }
    }

    override fun onRewardedVideoAdLoaded() {

    }

    override fun onRewardedVideoAdOpened() {
    }

    override fun onRewardedVideoStarted() {
    }

    override fun onRewardedVideoCompleted() {
        loadRewardedVideoAd()
        if (rewardv== 1) {
            var myIntent = Intent(baseContext, Compass::class.java)
            startActivity(myIntent)
            rewardv = 0
        }
    }


}
