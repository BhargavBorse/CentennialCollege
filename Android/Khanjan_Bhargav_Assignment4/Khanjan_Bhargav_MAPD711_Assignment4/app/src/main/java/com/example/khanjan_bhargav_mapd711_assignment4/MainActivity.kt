package com.example.khanjan_bhargav_mapd711_assignment4

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.khanjan_bhargav_mapd711_assignment4.model.Admin

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        val isAdmin = sharedPreferences.getBoolean("isAdmin", false)

        if (isLoggedIn) {
            if(isAdmin){
                val intent = Intent(this, AdminHomePage::class.java) // Replace HomePage::class.java with your home page activity
                startActivity(intent)
                finish() // Finish the current login activity
            }else{
                val intent = Intent(this, customerHomePage::class.java) // Replace HomePage::class.java with your home page activity
                startActivity(intent)
                finish() // Finish the current login activity
            }

        }else{
            setContentView(R.layout.activity_main)
            supportActionBar?.hide()
            val customerButton = findViewById<Button>(R.id.customerButton)
            val adminButton = findViewById<Button>(R.id.adminButton)

            customerButton.setOnClickListener {
                val intent = Intent(this, CustomerLogin::class.java)
                startActivity(intent)
            }

            adminButton.setOnClickListener {
                val intent = Intent(this, adminLogin::class.java)
                startActivity(intent)
            }
        }

    }



}