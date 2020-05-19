package com.skgezhil.allinone


import android.app.DownloadManager
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_webviewer.*


@Suppress("DEPRECATION")
class Webviewer : AppCompatActivity() {

    //cached references

    var url: String? = null
    private var imageUri: Uri? = null
    private fun Any.onReceiveValue(resultsArray: Array<Uri?>) {}

    //on create method

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()!!.hide() //hide the title bar
        setContentView(R.layout.activity_webviewer)

        // full screen and title bar

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //banner ad initialization

        MobileAds.initialize(this) {}

        //webview settings

        webview.webViewClient = mywebviewclient()
        webview.settings.javaScriptEnabled = true
        webview.settings.allowContentAccess = true
        webview.settings.javaScriptCanOpenWindowsAutomatically = true
        webview.settings.allowUniversalAccessFromFileURLs = true
        webview.settings.loadsImagesAutomatically = true
        webview.settings.savePassword = true
        webview.settings.allowFileAccess = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webview.settings.allowFileAccessFromFileURLs = true
        }

        //url loading

        url = intent.getStringExtra("webivewImprint");
        webview.loadUrl(url)

        //web chrome client

        webview.setWebChromeClient(object:WebChromeClient() {

            //file chooser

            override fun onShowFileChooser(
                webView: WebView,
                filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                var mFilePathCallback = filePathCallback
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("*/*")
                val PICKFILE_REQUEST_CODE = 100
                startActivityForResult(intent, PICKFILE_REQUEST_CODE)
                return true
            }
        })

        //file chooser result handler

        fun onActivityResult(requestCode: Int, resultCode: Int,
                             intent: Intent,
                             mFilePathCallback: Any): Boolean {
            var PICKFILE_REQUEST_CODE = null
            if (requestCode == PICKFILE_REQUEST_CODE)
            {
                val result = if (intent == null || resultCode != RESULT_OK)
                    null
                else
                    intent.getData()
                val resultsArray = arrayOfNulls<Uri>(1)
                resultsArray[0] = result
                mFilePathCallback.onReceiveValue(resultsArray)

            }
            return true
        }

            //web downloader

        webview.setDownloadListener(object : DownloadListener {
            override fun onDownloadStart(url: String, userAgent: String,
                                         contentDisposition: String, mimetype: String,
                                         contentLength: Long) {
                val request = DownloadManager.Request(Uri.parse(url))
                request.allowScanningByMediaScanner()

                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED) //Notify client once download is completed!
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, mimetype)
                val webview = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                webview.enqueue(request)
                Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show()
            }
        })
    }

    //back pressed

    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        }
        else {
            finish()
        }

    }

    //web view client

    @Suppress("OverridingDeprecatedMember")
    inner class mywebviewclient : WebViewClient(){
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)

        //url loading

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }

        //page start listener

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar.visibility = View.VISIBLE
            adView.visibility = View.GONE
        }

        //page finish listener

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
            adView.visibility = View.GONE
            val adRequest = AdRequest.Builder()
                .build()
                adView.loadAd(adRequest)

            //banner ad listeners

            adView.adListener = object: AdListener() {
                override fun onAdLoaded() {

                    Handler().postDelayed({
                        adView.visibility = View.VISIBLE
                    },10000)

                        Handler().postDelayed({
                            adView.visibility = View.GONE
                            adView.loadAd(adRequest)
                        }, 20000)
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
        }
    }



}
