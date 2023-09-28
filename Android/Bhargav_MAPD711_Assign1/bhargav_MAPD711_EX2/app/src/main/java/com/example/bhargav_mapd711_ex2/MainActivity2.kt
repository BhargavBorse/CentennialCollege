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

        // Creating a formatted text string by combining string resources from 'strings.xml'.
        val infoText = """
            ${getString(R.string.your_name)}
            ${getString(R.string.your_qualification)}
            ${getString(R.string.your_profession)}
            ${getString(R.string.your_hobby)}
            ${getString(R.string.your_dream_job)}
        """.trimIndent()

        // Setting the formatted text as the content for 'infoTextView'.
        infoTextView.text = infoText
    }
}