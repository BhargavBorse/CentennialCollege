package com.example.khanjan_bhargav_mapd711_assignment4

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.khanjan_bhargav_mapd711_assignment4.db.AdminRepository
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaDatabase
import com.example.khanjan_bhargav_mapd711_assignment4.model.Admin
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.AdminViewModel
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.AdminViewModelFactory
import kotlinx.coroutines.launch

class AdminRegistration : AppCompatActivity() {
    private lateinit var viewModel: AdminViewModel
    private lateinit var firstname: EditText
    private lateinit var lastName: EditText
    private lateinit var userName: EditText
    private lateinit var password: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_registration)

        supportActionBar?.hide()
        val repository =
            AdminRepository(PizzaDatabase.getDatabaseInstance(applicationContext).AdminDao())
        val viewModelFactory = AdminViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[AdminViewModel::class.java]
        firstname = findViewById(R.id.adminRegisterFirstName)
        lastName = findViewById(R.id.adminRegisterLastName)
        userName = findViewById(R.id.adminRegisterUserName)
        password = findViewById(R.id.adminRegisterPassword)


        val backButton = findViewById<Button>(R.id.adminRegisterBackButton)
        val register = findViewById<Button>(R.id.adminRegisterButton)

        register.setOnClickListener{
            registerAdmin()
        }
        backButton.setOnClickListener{
            finish();
        }

    }

    fun registerAdmin() {
        val userNameInput = userName.text.toString().trim()
        val passwordInput = password.text.toString().trim()
        val firstNameInput = firstname.text.toString().trim()
        val lastNameInput = lastName.text.toString().trim()


        // Regular expressions for validation
        val userNameRegex = Regex("^[a-zA-Z0-9_-]{3,15}\$")
        val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$")
        val nameRegex = Regex("^[a-zA-Z]+\$")


        val isValidUserName = userNameRegex.matches(userNameInput)
        val isValidPassword = passwordRegex.matches(passwordInput)
        val isValidFirstName = nameRegex.matches(firstNameInput)
        val isValidLastName = nameRegex.matches(lastNameInput)


        if (userNameInput.isEmpty() || passwordInput.isEmpty() || firstNameInput.isEmpty() ||
            lastNameInput.isEmpty()
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
        }else {
            // All fields are valid, create a Customer object and proceed to insert into the database
            viewModel.viewModelScope.launch {
                val isUsernameExists = viewModel.isAdminUsernameExists(userNameInput)
                if (isUsernameExists) {
                    // Show error message if the username already exists in the database
                    Toast.makeText(this@AdminRegistration, "Username already exists, please choose a different one", Toast.LENGTH_SHORT).show()
                } else {
                    // All fields are valid, proceed to insert the new customer
                    val newCustomer = Admin(
                        userName = userNameInput,
                        password = passwordInput,
                        firstname = firstNameInput,
                        lastname = lastNameInput
                    )
                    viewModel.insertAdmin(newCustomer)
                    finish()
                    Toast.makeText(this@AdminRegistration, "Successfully Registered", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

}
