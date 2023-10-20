// Name: Bhargav Borse
// Id: 301278352
// Mid-Test

package com.example.bhargav_mapd711_test

import android.content.SharedPreferences
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WatchResultActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var watchSelectionTextView: TextView
    private lateinit var loopSelectionTextView: TextView
    private lateinit var watchSizeTextView: TextView
    private lateinit var tradeOptionTextView: TextView
    private lateinit var webView: WebView
    private lateinit var totalPriceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_result)

        // Initialize TextViews and WebView
        watchSelectionTextView = findViewById(R.id.watchSelectionTextView)
        loopSelectionTextView = findViewById(R.id.loopSelectionTextView)
        watchSizeTextView = findViewById(R.id.watchSizeTextView)
        tradeOptionTextView = findViewById(R.id.tradeOptionTextView)
        webView = findViewById(R.id.webView)
        totalPriceTextView = findViewById(R.id.totalPriceTextView)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("WatchAppPreferences", MODE_PRIVATE)

        // Retrieve data from SharedPreferences
        val selectedWatch = sharedPreferences.getString("selectedWatch", "")
        val selectedLoop = sharedPreferences.getString("selectedLoop", "")
        val selectedSize = sharedPreferences.getString("selectedSize", "")
        val selectedTradeOption = sharedPreferences.getString("selectedTradeOption", "")

        // Display the selected options
        watchSelectionTextView.text = "Selected Apple Watch: $selectedWatch"
        loopSelectionTextView.text = "Selected Loop: $selectedLoop"
        watchSizeTextView.text = "Selected Watch Size: $selectedSize"
        tradeOptionTextView.text = "Trade Option: $selectedTradeOption"

        // Retrieve and display the total price
        val totalPrice = intent.getIntExtra("totalPrice", 0)
        totalPriceTextView.text = "Total Price: $$totalPrice"

        // Configure the WebView
        val showWebsite = sharedPreferences.getBoolean("showWebsite", true)
        if (showWebsite) {
            webView.settings.javaScriptEnabled = true // Enable JavaScript
            webView.webViewClient = WebViewClient()
            webView.loadUrl("https://www.apple.com/ca/")
        }
    }
}
