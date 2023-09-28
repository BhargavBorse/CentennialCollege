package com.example.bhargav_MAPD711_EX1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Finding and initializing the Button view with the ID 'sendButton'.
        val btnSend = findViewById<Button>(R.id.sendButton)

        // Finding and initializing the EditText views with their respective IDs.
        val fullName = findViewById<EditText>(R.id.fullNameEditText)
        val qualification = findViewById<EditText>(R.id.qualificationEditText)
        val profession = findViewById<EditText>(R.id.professionEditText)
        val hobby = findViewById<EditText>(R.id.hobbyEditText)
        val dreamJob = findViewById<EditText>(R.id.dreamJobEditText)

        // Setting a click listener for the 'btnSend' Button.
        btnSend.setOnClickListener(){

            // Creating an Intent to start 'MainActivity2'.
            val intent = Intent(this, MainActivity2::class.java)

            // Passing user information as extras in the intent
            intent.putExtra("fullName", fullName.text.toString())
            intent.putExtra("qualification", qualification.text.toString())
            intent.putExtra("profession", profession.text.toString())
            intent.putExtra("hobby", hobby.text.toString())
            intent.putExtra("dreamJob", dreamJob.text.toString())

            // Starting 'MainActivity2' with the intent.
            startActivity(intent)
        }
    }
}