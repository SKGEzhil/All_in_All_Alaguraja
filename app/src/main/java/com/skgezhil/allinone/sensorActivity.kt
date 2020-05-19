package com.skgezhil.allinone

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

        //interstitial ads

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-7716695643466392/5373428915"

        // full screen and actionbar

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val actionbar = supportActionBar
        actionbar?.setBackgroundDrawable(resources.getDrawable(R.drawable.actionbar))

        //button click listeners

        compassbtn?.setOnClickListener {
            Toast.makeText(applicationContext,"Loading....",Toast.LENGTH_LONG).show()
            loadRewardedVideoAd()
            if (mRewardedVideoAd.isLoaded) {
                mRewardedVideoAd.show()
            }
        }
        pedometer?.setOnClickListener {
            var myIntent = Intent(baseContext, Pedometer::class.java)
            startActivity(myIntent)
        }
        barcodescanner?.setOnClickListener {
            Toast.makeText(applicationContext,"Loading....",Toast.LENGTH_LONG).show()
            loadinterstitialads()
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.")
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
                var myIntent = Intent(baseContext, BarcodeScanner::class.java)
                startActivity(myIntent)
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
                var myIntent = Intent(baseContext, BarcodeScanner::class.java)
                startActivity(myIntent)
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
        var myIntent = Intent(baseContext, Compass::class.java)
        startActivity(myIntent)
    }

    override fun onRewardedVideoAdFailedToLoad(errorCode: Int) {
        var myIntent = Intent(baseContext, Compass ::class.java)
        startActivity(myIntent)
    }

    override fun onRewardedVideoAdLoaded() {
        mRewardedVideoAd.show()
    }

    override fun onRewardedVideoAdOpened() {
    }

    override fun onRewardedVideoStarted() {
    }

    override fun onRewardedVideoCompleted() {
        var myIntent = Intent(baseContext, Compass::class.java)
        startActivity(myIntent)
    }


}
