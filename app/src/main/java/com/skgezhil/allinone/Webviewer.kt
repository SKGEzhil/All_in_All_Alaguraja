package com.skgezhil.allinone


import android.app.DownloadManager
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_webviewer.*


@Suppress("DEPRECATION")
class Webviewer : AppCompatActivity() {
    var url: String? = null
    private var imageUri: Uri? = null
    private fun Any.onReceiveValue(resultsArray: Array<Uri?>) {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()!!.hide() //hide the title bar
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_webviewer)
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
        url = intent.getStringExtra("webivewImprint");
        webview.loadUrl(url)

        webview.setWebChromeClient(object:WebChromeClient() {
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
    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        }
        else {
            finish()
        }

    }
    @Suppress("OverridingDeprecatedMember")
    inner class mywebviewclient : WebViewClient(){
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
        }
    }



}
