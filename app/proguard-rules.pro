# Add project specific ProGuard rules here.
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void onReceivedSslError(android.webkit.WebView, android.webkit.SslErrorHandler, android.net.http.SslError);
}
-keepclassmembers class * implements android.webkit.WebChromeClient {
    public void onReceivedTitle(android.webkit.WebView, java.lang.String);
}