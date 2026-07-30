package com.nnbot.chat

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("SetJavaScriptEnabled")
class MainActivity : AppCompatActivity() {

    private lateinit var web: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        web = findViewById(R.id.web)
        val settings = web.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.allowFileAccess = false
        settings.builtInZoomControls = false
        settings.cacheMode = android.webkit.WebSettings.LOAD_DEFAULT
        settings.mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE

        web.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError
            ) {
                if (request.isForMainFrame) {
                    view.loadDataWithBaseURL(
                        null,
                        """
                        <html><body style='background:#0b1220;color:#e6eaf2;padding:20px;font-family:sans-serif;text-align:center;margin-top:20%'>
                            <h2>Ошибка соединения</h2>
                            <p style='color:#7dd3fc'>${error.description}</p>
                            <p style='color:#64748b;font-size:12px'>${request.url}</p>
                            <p style='margin-top:30px'><a href='#' onclick='location.reload()' style='color:#38bdf8'>Повторить</a></p>
                        </body></html>
                        """.trimIndent(),
                        "text/html",
                        "UTF-8",
                        null
                    )
                }
            }

            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: android.net.http.SslError) {
                if (BuildConfig.DEBUG) {
                    handler.proceed()
                } else {
                    handler.cancel()
                }
            }
        }

        web.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                title?.let { supportActionBar?.title = it }
            }
        }

        web.loadUrl("https://chat.nnbot.fun")
    }

    override fun onBackPressed() {
        if (::web.isInitialized && web.canGoBack()) web.goBack() else super.onBackPressed()
    }

    override fun onDestroy() {
        if (::web.isInitialized) {
            web.loadUrl("about:blank")
            web.stopLoading()
            web.webViewClient = android.webkit.WebViewClient()
            web.destroy()
        }
        super.onDestroy()
    }
}