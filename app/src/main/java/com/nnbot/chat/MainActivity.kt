package com.nnbot.chat

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                    """
                    <html><body style='background:#0b1220;color:#e6eaf2;padding:20px;font-family:sans-serif;text-align:center;margin-top:20%'>
                        <h2>Ошибка соединения</h2>
                        <p style='color:#7dd3fc'>${description ?: "Неизвестная ошибка"}</p>
                        <p style='color:#64748b;font-size:12px'>${failingUrl ?: ""}</p>
                        <p style='margin-top:30px'><a href='#' onclick='location.reload()' style='color:#38bdf8'>Повторить</a></p>
                    </body></html>
                    """.trimIndent(),
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
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val web = findViewById<WebView>(R.id.web)
        if (web.canGoBack()) {
            web.goBack()
        } else {
            super.onBackPressed()
        }
    }
}