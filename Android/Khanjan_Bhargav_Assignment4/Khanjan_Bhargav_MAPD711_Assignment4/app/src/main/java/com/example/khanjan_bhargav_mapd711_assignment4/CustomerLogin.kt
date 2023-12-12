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
import com.example.khanjan_bhargav_mapd711_assignment4.db.CustomerRepository
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaDatabase
import com.example.khanjan_bhargav_mapd711_assignment4.model.Customer
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.CustomerViewModel
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.CustomerViewModelFactory
import kotlinx.coroutines.launch

class CustomerLogin : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var viewModel: CustomerViewModel
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_login)
        supportActionBar?.hide()

        userName = findViewById(R.id.customerLoginUserName)
        password = findViewById(R.id.customerLoginPassword)

        val repository = CustomerRepository(PizzaDatabase.getDatabaseInstance(applicationContext).customerDao())
        val viewModelFactory = CustomerViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CustomerViewModel::class.java)

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            // User is already logged in, navigate to the home page
            val intent = Intent(this, customerHomePage::class.java) // Replace HomePage::class.java with your home page activity
            startActivity(intent)
            finish() // Finish the current login activity
        }

        val backButton = findViewById<Button>(R.id.customerLoginBackButton)
        val login = findViewById<TextView>(R.id.customerLoginButton)
        val register = findViewById<TextView>(R.id.goToLoginPageButton)

        backButton.setOnClickListener {
            finish()
        }

        login.setOnClickListener {
            loginCheck()
        }

        register.setOnClickListener{
            val intent = Intent(this, CustomerRegistration::class.java)
            startActivity(intent)
        }
    }

    private fun loginCheck() {
        val userNameInput = userName.text.toString().trim()
        val passwordInput = password.text.toString().trim()

        viewModel.viewModelScope.launch {
            val isUsernameExists = viewModel.isUsernameAndPasswordExists(userNameInput, passwordInput)
            val userData = viewModel.getUserByUsername(userNameInput)
            if (isUsernameExists) {
                // Store login information in SharedPreferences on successful login
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.putString("username", userNameInput)
                editor.putBoolean("isAdmin", false)
                editor.putInt("userId", userData!!.customerId)
                editor.apply()

                // Navigate to the home page
                val intent = Intent(this@CustomerLogin, customerHomePage::class.java) // Replace HomePage::class.java with your home page activity
                startActivity(intent)
                finish() // Finish the current login activity
            } else {
                Toast.makeText(this@CustomerLogin, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}