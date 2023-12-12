package com.example.khanjan_bhargav_mapd711_assignment4

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.khanjan_bhargav_mapd711_assignment4.db.CustomerRepository
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaDatabase
import com.example.khanjan_bhargav_mapd711_assignment4.model.Customer
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.CustomerViewModel
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.CustomerViewModelFactory
import kotlinx.coroutines.launch

class CustomerProfile : AppCompatActivity() {
    private lateinit var viewModel: CustomerViewModel
    private lateinit var firstname: EditText
    private lateinit var lastName: EditText
    private lateinit var password: EditText
    private lateinit var address: EditText
    private lateinit var city: EditText
    private lateinit var postalCode: EditText

    lateinit var userName: String
    var customerId : Int = 0

    private lateinit var sharedPreferences: SharedPreferences
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_profile)

        supportActionBar?.hide()

        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        val repository =
            CustomerRepository(PizzaDatabase.getDatabaseInstance(applicationContext).customerDao())
        val viewModelFactory = CustomerViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[CustomerViewModel::class.java]


        firstname = findViewById(R.id.customerProfileFirstName)
        lastName = findViewById(R.id.customerProfileLastName)
        password = findViewById(R.id.customerProfilePassword)
        address = findViewById(R.id.customerProfileAddress)
        city = findViewById(R.id.customerProfileCity)
        postalCode = findViewById(R.id.customerProfilePostalCode)

        val backButton = findViewById<ImageButton>(R.id.customerProfileBackButton)
        val editProfile = findViewById<Button>(R.id.customerProfileButton)

        val backImageStream = assets.open("left-arrow.png")
        val bitmap = BitmapFactory.decodeStream(backImageStream)
        backButton.setImageBitmap(bitmap)

        editProfile.setOnClickListener{
            registerCustomer()
        }
        backButton.setOnClickListener{
            finish();
        }
        fun String?.toEditable(): Editable? {
            return this?.let { Editable.Factory.getInstance().newEditable(it) }
        }
        viewModel.viewModelScope.launch {
            customerId = sharedPreferences.getInt("userId", 0)
            val userData = viewModel.getUserByUserId(customerId)
            userName = userData!!.userName

            firstname.text = userData.firstname.toEditable()
            lastName.text = userData.lastname.toEditable()
            password.text = userData.password.toEditable()
            address.text = userData.address.toEditable()
            postalCode.text = userData.postalCode.toEditable()
            city.text = userData.city.toEditable()
            // Use the userData here
        }


    }
    fun registerCustomer() {
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


        val isValidPassword = passwordRegex.matches(passwordInput)
        val isValidFirstName = nameRegex.matches(firstNameInput)
        val isValidLastName = nameRegex.matches(lastNameInput)
        val isValidAddress = addressRegex.matches(addressInput)
        val isValidCity = nameRegex.matches(cityInput)
        val isValidPostalCode = postalCodeRegex.matches(postalCodeInput)

        if (passwordInput.isEmpty() || firstNameInput.isEmpty() ||
            lastNameInput.isEmpty() || addressInput.isEmpty() || cityInput.isEmpty() ||
            postalCodeInput.isEmpty()
        ) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
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

                    // All fields are valid, proceed to insert the new customer
                    val newCustomer = Customer(
                        customerId = customerId,
                        userName = userName,
                        password = passwordInput,
                        firstname = firstNameInput,
                        lastname = lastNameInput,
                        address = addressInput,
                        city = cityInput,
                        postalCode = postalCodeInput
                    )
                    viewModel.updateUser(newCustomer)
                    finish()
                    Toast.makeText(this@CustomerProfile, "Successfully Edited", Toast.LENGTH_SHORT).show()

            }

        }
    }
}