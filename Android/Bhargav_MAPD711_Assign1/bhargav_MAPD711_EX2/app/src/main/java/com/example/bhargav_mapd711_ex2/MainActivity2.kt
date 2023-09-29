package com.example.bhargav_mapd711_ex2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Retrieving and displaying the string values from resources
        val infoTextView = findViewById<TextView>(R.id.infoTextView)

        //stored the details in value, got from main activity
        val details = intent.getStringExtra("details")

        // Setting the formatted text as the content for 'infoTextView'.
        infoTextView.text = details
    }
}