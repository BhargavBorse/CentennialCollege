package com.example.bhargav_mapd711_assignment2_pizzaonline

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CustomerInfoActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var nameEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var postalCodeEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var cardTypeSpinner: Spinner
    private lateinit var creditCardNumberEditText: EditText
    private lateinit var expiryDateEditText: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_info)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("OrderPrefs", Context.MODE_PRIVATE)

        // Initialize UI elements
        nameEditText = findViewById(R.id.nameEditText)
        addressEditText = findViewById(R.id.addressEditText)
        postalCodeEditText = findViewById(R.id.postalCodeEditText)
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText)
        cardTypeSpinner = findViewById(R.id.cardTypeSpinner)
        creditCardNumberEditText = findViewById(R.id.creditCardNumberEditText)
        expiryDateEditText = findViewById(R.id.expiryDateEditText)
        submitButton = findViewById(R.id.submitButton)

        // Set up the Spinner for card types
        val cardTypes = resources.getStringArray(R.array.card_types)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cardTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cardTypeSpinner.adapter = adapter

        submitButton.setOnClickListener {
            val unfilledFields = validateCustomerInfo()
            if (unfilledFields.isEmpty()) {
                val inputName = nameEditText.text.toString()
                val inputAddress = addressEditText.text.toString()
                val inputPostalCode = postalCodeEditText.text.toString()
                val inputPhoneNumber = phoneNumberEditText.text.toString()
                val inputCardType = cardTypeSpinner.selectedItem.toString()
                val inputCreditCardNumber = creditCardNumberEditText.text.toString()
                val inputExpiryDate = expiryDateEditText.text.toString()

                val editor = sharedPreferences.edit()
                editor.putString("customerName", inputName)
                editor.putString("customerAddress", inputAddress)
                editor.putString("customerPostalCode", inputPostalCode)
                editor.putString("customerPhoneNumber", inputPhoneNumber)
                editor.putString("selectedCardType", inputCardType)
                editor.putString("customerCreditCardNumber", inputCreditCardNumber)
                editor.putString("customerExpiryDate", inputExpiryDate)
                editor.apply()

                val intent = Intent(this, ConfirmationActivity::class.java)
                startActivity(intent)

                // Display a confirmation message or perform other actions
                Toast.makeText(this, "Customer information saved.", Toast.LENGTH_SHORT).show()
            } else {
                val errorMessage = "Please fill in the following fields: ${unfilledFields.joinToString(", ")}"
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateCustomerInfo(): List<String> {
        val unfilledFields = mutableListOf<String>()
        if (nameEditText.text.isBlank()) {
            unfilledFields.add("Name")
        }
        if (addressEditText.text.isBlank()) {
            unfilledFields.add("Address")
        }
        if (postalCodeEditText.text.isBlank()) {
            unfilledFields.add("Postal Code")
        }
        if (phoneNumberEditText.text.isBlank()) {
            unfilledFields.add("Phone Number")
        } else if (!isPhoneNumberValid(phoneNumberEditText.text.toString())) {
            unfilledFields.add("Invalid Phone Number")
        }
        if (creditCardNumberEditText.text.isBlank()) {
            unfilledFields.add("Credit Card Number")
        } else if (!isCreditCardNumberValid(creditCardNumberEditText.text.toString())) {
            unfilledFields.add("Invalid Credit Card Number")
        }
        if (expiryDateEditText.text.isBlank()) {
            unfilledFields.add("Expiry Date")
        } else if (!isExpiryDateValid(expiryDateEditText.text.toString())) {
            unfilledFields.add("Invalid Expiry Date")
        }
        return unfilledFields
    }

    private fun isPhoneNumberValid(phone: String): Boolean {
        return Patterns.PHONE.matcher(phone).matches()
    }

    private fun isCreditCardNumberValid(cardNumber: String): Boolean {
        // Implement your credit card validation logic here
        // For simplicity, you can check the length
        return cardNumber.length == 16
    }

    private fun isExpiryDateValid(expiryDate: String): Boolean {
        val dateFormat = SimpleDateFormat("MM/yy", Locale.getDefault())
        dateFormat.isLenient = false
        return try {
            val date = dateFormat.parse(expiryDate)
            val currentDate = Date()
            date != null && date.after(currentDate)
        } catch (e: Exception) {
            false
        }
    }
}
