package com.example.application.braintradeblogs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_gallery.*
import android.webkit.WebViewClient


class GalleryActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        println("Activity Started Successfully")

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        getIncomingIntent()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getIncomingIntent() {

        if (intent.hasExtra("textOk")) {  //checking if it is valid intent or not

            val links = intent.getStringExtra("textOk")

            val webSettings = webView.settings
            webSettings.javaScriptEnabled = true
            webView.webViewClient = WebViewClient()
            webView.loadUrl(links)
        }
    }

}