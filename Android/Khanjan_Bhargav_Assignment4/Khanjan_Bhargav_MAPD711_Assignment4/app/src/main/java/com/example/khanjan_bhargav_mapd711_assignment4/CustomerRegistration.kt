package com.example.khanjan_bhargav_mapd711_assignment4

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaDatabase
import com.example.khanjan_bhargav_mapd711_assignment4.db.CustomerRepository
import com.example.khanjan_bhargav_mapd711_assignment4.model.Customer
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.CustomerViewModel
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.CustomerViewModelFactory
import kotlinx.coroutines.launch

class CustomerRegistration : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var viewModel: CustomerViewModel
    private lateinit var firstname: EditText
    private lateinit var lastName: EditText
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var address: EditText
    private lateinit var city: EditText
    private lateinit var postalCode: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_registration)
        supportActionBar?.hide()

        val repository =
            CustomerRepository(PizzaDatabase.getDatabaseInstance(applicationContext).customerDao())
        val viewModelFactory = CustomerViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[CustomerViewModel::class.java]

        firstname = findViewById(R.id.customerRegisterFirstName)
        lastName = findViewById(R.id.customerRegisterLastName)
        userName = findViewById(R.id.customerRegisterUserName)
        password = findViewById(R.id.customerRegisterPassword)
        address = findViewById(R.id.customerRegisterAddress)
        city = findViewById(R.id.customerRegisterCity)
        postalCode = findViewById(R.id.customerRegisterPostalCode)

        val backButton = findViewById<Button>(R.id.customerRegisterBackButton)
        val register = findViewById<Button>(R.id.customerRegisterButton)

        register.setOnClickListener{
            registerCustomer()
        }
        backButton.setOnClickListener{
            finish();
        }

    }

    fun registerCustomer() {
        val userNameInput = userName.text.toString().trim()
        val passwordInput = password.text.toString().trim()
        val firstNameInput = firstname.text.toString().trim()
        val lastNameInput = lastName.text.toString().trim()
        val addressInput = address.text.toString().trim()
        val cityInput = city.text.toString().trim()
        val postalCodeInput = postalCode.text.toString().trim()

        // Regular expressions for validation
        val userNameRegex = Regex("^[a-zA-Z0-9_-]{3,15}\$")
        val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$")
        val nameRegex = Regex("^[a-zA-Z]+\$")
        val addressRegex = Regex("^\\d+\\s[A-z]+\\s[A-z]+")
        val postalCodeRegex = Regex("^[A-Za-z]\\d[A-Za-z] \\d[A-Za-z]\\d\$")

        val isValidUserName = userNameRegex.matches(userNameInput)
        val isValidPassword = passwordRegex.matches(passwordInput)
        val isValidFirstName = nameRegex.matches(firstNameInput)
        val isValidLastName = nameRegex.matches(lastNameInput)
        val isValidAddress = addressRegex.matches(addressInput)
        val isValidCity = nameRegex.matches(cityInput)
        val isValidPostalCode = postalCodeRegex.matches(postalCodeInput)

        if (userNameInput.isEmpty() || passwordInput.isEmpty() || firstNameInput.isEmpty() ||
            lastNameInput.isEmpty() || addressInput.isEmpty() || cityInput.isEmpty() ||
            postalCodeInput.isEmpty()
        ) {
             Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
        } else if (!isValidUserName) {
             Toast.makeText(this, "Invalid username format", Toast.LENGTH_SHORT).show()
        } else if (!isValidPassword) {
            Toast.makeText(this, "Invalid password format", Toast.LENGTH_SHORT).show()
        } else if (!isValidFirstName) {
            // Show error message for invalid first name format
            Toast.makeText(this, "invalid first name format", Toast.LENGTH_SHORT).show()
        } else if (!isValidLastName) {
            // Show error message for invalid last name format
            Toast.makeText(this, "Invalid last name format", Toast.LENGTH_SHORT).show()
        } else if (!isValidAddress) {
            // Show error message for invalid address format
            Toast.makeText(this, "Invalid address format", Toast.LENGTH_SHORT).show()
        } else if (!isValidCity) {
            // Show error message for invalid city format
            Toast.makeText(this, "Invalid city format", Toast.LENGTH_SHORT).show()
        } else if (!isValidPostalCode) {
            // Show error message for invalid postal code format
            Toast.makeText(this, "Invalid Postal code format", Toast.LENGTH_SHORT).show()
        } else {
            // All fields are valid, create a Customer object and proceed to insert into the database
            viewModel.viewModelScope.launch {
                val isUsernameExists = viewModel.isUsernameExists(userNameInput)
                if (isUsernameExists) {
                    // Show error message if the username already exists in the database
                    Toast.makeText(this@CustomerRegistration, "Username already exists, please choose a different one", Toast.LENGTH_SHORT).show()
                } else {
                    // All fields are valid, proceed to insert the new customer
                    val newCustomer = Customer(
                        userName = userNameInput,
                        password = passwordInput,
                        firstname = firstNameInput,
                        lastname = lastNameInput,
                        address = addressInput,
                        city = cityInput,
                        postalCode = postalCodeInput
                    )
                    viewModel.insertUser(newCustomer)
                    finish()
                    Toast.makeText(this@CustomerRegistration, "Successfully Registered", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }


}