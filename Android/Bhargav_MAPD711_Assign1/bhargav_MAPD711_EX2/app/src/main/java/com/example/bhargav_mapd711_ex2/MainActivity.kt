package com.example.bhargav_mapd711_ex2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sendButton = findViewById<Button>(R.id.sendButton)

        // Retrieving data from strings.xml
        val yourName = getString(R.string.your_name)
        val yourQualification = getString(R.string.your_qualification)
        val yourProfession = getString(R.string.your_profession)
        val yourHobby = getString(R.string.your_hobby)
        val yourDreamJob = getString(R.string.your_dream_job)

        // Displaying the data in a TextView
        val infoTextView = findViewById<TextView>(R.id.infoTextView)
        val infoText = """
            $yourName
            $yourQualification
            $yourProfession
            $yourHobby
            $yourDreamJob
        """.trimIndent()

        infoTextView.text = infoText

        sendButton.setOnClickListener(View.OnClickListener {
            // Creating an Intent to start the second activity
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)

        })
    }
}