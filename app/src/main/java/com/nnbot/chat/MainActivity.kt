package com.nnbot.chat
import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val web = findViewById<WebView>(R.id.web)
        web.settings.javaScriptEnabled = true
        web.settings.domStorageEnabled = true
        web.loadUrl("https://chat.nnbot.fun")
    }
}
