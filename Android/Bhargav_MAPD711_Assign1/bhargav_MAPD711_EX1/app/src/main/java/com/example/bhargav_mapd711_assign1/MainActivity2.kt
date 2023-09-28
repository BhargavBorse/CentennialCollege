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

        val intent = intent
        val fullName = intent.getStringExtra("fullName")
        val qualification = intent.getStringExtra("qualification")
        val profession = intent.getStringExtra("profession")
        val hobby = intent.getStringExtra("hobby")
        val dreamJob = intent.getStringExtra("dreamJob")
        val displayTextView = findViewById<TextView>(R.id.displayTextView)

        val displayText = "Full Name: $fullName\nQualification: $qualification\nProfession: $profession\nHobby: $hobby\nDream Job: $dreamJob"
        displayTextView.text = displayText
    }
}