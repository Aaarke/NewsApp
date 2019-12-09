package com.example.newsapp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.fragment_web_view.webView


class WebViewActivity : AppCompatActivity() {
    private var dialog: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val actionBar = supportActionBar
        dialog = ProgressBar(this, null, android.R.attr.progressBarStyleLarge)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = getString(R.string.my_news)
        loadWebView(intent.getStringExtra("url"))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun loadWebView(url: String) {
        webView.webViewClient = MyWebViewClient()
        webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webView.webChromeClient = WebChromeClient()
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                // Keep WebView hidden when the url is loading
                webView.visibility = View.INVISIBLE
                // Make ProgressBar visible
                progressBar.visibility = View.VISIBLE
                progressBar.progress = 0
                progressBar.incrementProgressBy(progress)

                if (progress == 100 && progressBar.visibility == View.VISIBLE) {
                    // Make ProgressBar invisible and WebView visible
                    progressBar.visibility = View.INVISIBLE
                    webView.visibility = View.VISIBLE
                }
            }
        }
        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.isVerticalScrollBarEnabled = true
        webView.isHorizontalScrollBarEnabled = true
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.loadUrl(url)
    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.loadUrl(request.url.toString())
                Log.e("URL", request.url.toString() + "")

            }
            return true
        }


        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            Log.e("URL", url + "")
            return true
        }


    }

}
