package com.example.bhargav_mapd711_ex2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Retrieve and display the string values from resources
        val infoTextView = findViewById<TextView>(R.id.infoTextView)
        val infoText = """
            ${getString(R.string.your_name)}
            ${getString(R.string.your_qualification)}
            ${getString(R.string.your_profession)}
            ${getString(R.string.your_hobby)}
            ${getString(R.string.your_dream_job)}
        """.trimIndent()

        infoTextView.text = infoText
    }
}