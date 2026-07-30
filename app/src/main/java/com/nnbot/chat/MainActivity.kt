package com.nnbot.chat

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebChromeClient
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val web = findViewById<WebView>(R.id.web)
            web.settings.javaScriptEnabled = true
            web.settings.domStorageEnabled = true
            web.settings.allowFileAccess = false
            web.settings.builtInZoomControls = false

            web.webViewClient = object : WebViewClient() {
                override fun onReceivedError(
                    view: WebView?,
                    errorCode: Int,
                    description: String?,
                    failingUrl: String?
                ) {
                    view?.loadDataWithBaseURL(
                        null,
                        "<html><body style='background:#0b1220;color:#e6eaf2;padding:20px;font-family:sans-serif;text-align:center;margin-top:40%'>" +
                                "<h2>Ошибка соединения</h2>" +
                                "<p style='color:#7dd3fc'>$description</p>" +
                                "<p style='color:#64748b;font-size:12px'>$failingUrl</p>" +
                                "</body></html>",
                        "text/html", "UTF-8", null
                    )
                }
            }

            web.webChromeClient = object : WebChromeClient() {
                override fun onReceivedTitle(view: WebView?, title: String?) {
                    supportActionBar?.title = title
                }
            }

            web.loadUrl("https://chat.nnbot.fun")

        } catch (e: Exception) {
            e.printStackTrace()
            AlertDialog.Builder(this)
                .setTitle("Ошибка запуска")
                .setMessage("${e.message}")
                .setPositiveButton("Закрыть") { _, _ -> finish() }
                .show()
        }
    }

    override fun onBackPressed() {
        val web = findViewById<WebView>(R.id.web)
        if (web.canGoBack()) {
            web.goBack()
        } else {
            super.onBackPressed()
        }
    }
}