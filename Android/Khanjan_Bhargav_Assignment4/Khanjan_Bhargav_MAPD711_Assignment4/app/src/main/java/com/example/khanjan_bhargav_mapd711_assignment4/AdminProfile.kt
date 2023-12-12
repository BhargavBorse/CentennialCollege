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
import com.example.khanjan_bhargav_mapd711_assignment4.db.AdminRepository
import com.example.khanjan_bhargav_mapd711_assignment4.db.CustomerRepository
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaDatabase
import com.example.khanjan_bhargav_mapd711_assignment4.model.Admin
import com.example.khanjan_bhargav_mapd711_assignment4.model.Customer
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.AdminViewModel
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.AdminViewModelFactory
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.CustomerViewModel
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.CustomerViewModelFactory
import kotlinx.coroutines.launch

class AdminProfile : AppCompatActivity() {

    private lateinit var viewModel: AdminViewModel
    private lateinit var firstname: EditText
    private lateinit var lastName: EditText
    private lateinit var password: EditText

    lateinit var userName: String
    var customerId : Int = 0
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_profile)

        supportActionBar?.hide()

        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        val repository =
            AdminRepository(PizzaDatabase.getDatabaseInstance(applicationContext).AdminDao())
        val viewModelFactory = AdminViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[AdminViewModel::class.java]


        firstname = findViewById(R.id.adminProfileFirstName)
        lastName = findViewById(R.id.adminProfileLastName)
        password = findViewById(R.id.adminProfilePassword)

        val backButton = findViewById<ImageButton>(R.id.adminProfileBackButton)
        val editProfile = findViewById<Button>(R.id.adminProfileButton)

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
            // Use the userData here
        }


    }

    fun registerCustomer() {
        val passwordInput = password.text.toString().trim()
        val firstNameInput = firstname.text.toString().trim()
        val lastNameInput = lastName.text.toString().trim()

        // Regular expressions for validation
        val userNameRegex = Regex("^[a-zA-Z0-9_-]{3,15}\$")
        val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$")
        val nameRegex = Regex("^[a-zA-Z]+\$")
        val addressRegex = Regex("^\\d+\\s[A-z]+\\s[A-z]+")
        val postalCodeRegex = Regex("^[A-Za-z]\\d[A-Za-z] \\d[A-Za-z]\\d\$")


        val isValidPassword = passwordRegex.matches(passwordInput)
        val isValidFirstName = nameRegex.matches(firstNameInput)
        val isValidLastName = nameRegex.matches(lastNameInput)

        if (passwordInput.isEmpty() || firstNameInput.isEmpty() ||
            lastNameInput.isEmpty()
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
        } else {
            // All fields are valid, create a Customer object and proceed to insert into the database
            viewModel.viewModelScope.launch {

                // All fields are valid, proceed to insert the new customer
                val newCustomer = Admin(
                    employeeId = customerId,
                    userName = userName,
                    password = passwordInput,
                    firstname = firstNameInput,
                    lastname = lastNameInput
                )
                viewModel.updateUser(newCustomer)
                finish()
                Toast.makeText(this@AdminProfile, "Successfully Edited", Toast.LENGTH_SHORT).show()

            }

        }
    }
}