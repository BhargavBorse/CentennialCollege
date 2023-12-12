package com.example.khanjan_bhargav_mapd711_assignment4

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.khanjan_bhargav_mapd711_assignment4.db.AdminRepository
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaDatabase
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.AdminViewModel
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.AdminViewModelFactory
import kotlinx.coroutines.launch

class adminLogin : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    private lateinit var viewModel: AdminViewModel
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
        supportActionBar?.hide()
        userName = findViewById(R.id.adminLoginUserName)
        password = findViewById(R.id.adminLoginPassword)

        val repository = AdminRepository(PizzaDatabase.getDatabaseInstance(applicationContext).AdminDao())
        val viewModelFactory = AdminViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AdminViewModel::class.java)
        val backButton = findViewById<Button>(R.id.adminLoginBackButton)
        val login = findViewById<TextView>(R.id.adminLoginButton)
        val register = findViewById<TextView>(R.id.adminGoToMainPageButton)

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            // User is already logged in, navigate to the home page
            val intent = Intent(this, AdminHomePage::class.java) // Replace HomePage::class.java with your home page activity
            startActivity(intent)
            finish() // Finish the current login activity
        }

        backButton.setOnClickListener{
            finish();
        }
        register.setOnClickListener{
            val intent = Intent(this,AdminRegistration::class.java)
            startActivity(intent)
        }

        login.setOnClickListener{
            loginCheck()
        }
    }
    private fun loginCheck() {
        val userNameInput = userName.text.toString().trim()
        val passwordInput = password.text.toString().trim()

        viewModel.viewModelScope.launch {
            val isUsernameExists = viewModel.isAdminUsernameAndPasswordExists(userNameInput, passwordInput)
            if (isUsernameExists) {
            val getUserDetails =  viewModel.getAdminUsernameAndPasswordExists(userNameInput, passwordInput)

                val userData = viewModel.getUserByUsername(userNameInput)
                // Store login information in SharedPreferences on successful login
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.putString("username", getUserDetails?.userName.toString()) // Store username for reference
                editor.putBoolean("isAdmin", true)
                editor.putInt("userId", userData!!.employeeId)// Store username for reference
                editor.apply()

                // Navigate to the home page
                val intent = Intent(this@adminLogin, AdminHomePage::class.java) // Replace HomePage::class.java with your home page activity
                startActivity(intent)
                finish() // Finish the current login activity
            } else {
                Toast.makeText(this@adminLogin, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}