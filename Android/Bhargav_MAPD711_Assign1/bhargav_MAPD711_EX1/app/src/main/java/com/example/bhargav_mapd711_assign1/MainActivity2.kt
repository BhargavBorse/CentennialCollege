package com.example.bhargav_MAPD711_EX1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Create a new variable 'intent' and assign it the value of the existing intent.
        val intent = intent

        // Retrieving the value associated with the key from the intent and storing it in the variable.
        val fullName = intent.getStringExtra("fullName")
        val qualification = intent.getStringExtra("qualification")
        val profession = intent.getStringExtra("profession")
        val hobby = intent.getStringExtra("hobby")
        val dreamJob = intent.getStringExtra("dreamJob")

        // Finding the TextView with the ID 'displayTextView' in the layout and creating a reference to it.
        val displayTextView = findViewById<TextView>(R.id.displayTextView)

        // Creating a formatted text string by combining various user information variables.
        val displayText = "Full Name: $fullName\nQualification: $qualification\nProfession: $profession\nHobby: $hobby\nDream Job: $dreamJob"

        // Setting the formatted text as the content for the 'displayTextView'.
        displayTextView.text = displayText
    }
}