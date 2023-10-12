package com.example.bhargav_mapd711_assignment2_pizzaonline

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


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

                val orderSummary = createOrderSummary(inputName)
                displayOrderSummary(orderSummary)

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
        }
        if (creditCardNumberEditText.text.isBlank()) {
            unfilledFields.add("Credit Card Number")
        }
        if (expiryDateEditText.text.isBlank()) {
            unfilledFields.add("Expiry Date")
        }
        return unfilledFields
    }

    private fun createOrderSummary(customerName: String): String {
        val selectedPizzaType = sharedPreferences.getString("selectedPizzaType", "")
        val selectedSize = sharedPreferences.getString("selectedSize", "")
        val defaultValue: Set<String> = HashSet() // Default value if the preference is not set

        val toppingsTextArray: Set<String>? = sharedPreferences.getStringSet("selectedToppings", defaultValue);
        val delimiter = ", " // You can choose any delimiter you like
        val toppingsText = setToStringWithSeparator(toppingsTextArray!!, delimiter)



//        val selectedToppings = sharedPreferences.getString("selectedToppings", "")

//        val toppingsText = if (!selectedToppings.isNullOrBlank()) {
//            "Toppings: $selectedToppings"
//        } else {
//            "No additional toppings"
//        }

        return "Customer Name: $customerName\n" +
                "Type of Pizza: $selectedPizzaType\n" +
                "Size of Pizza: $selectedSize\n" +
                "$toppingsText\n" +
                "Customer Address: ${addressEditText.text}\n" +
                "Thank you for your order!"
    }
    fun setToStringWithSeparator(set: Set<String?>, separator: String): String {
        return set.filterNotNull().joinToString(separator)
    }
    private fun displayOrderSummary(orderSummary: String) {
        val orderSummaryTextView = findViewById<TextView>(R.id.orderSummaryTextView)
        orderSummaryTextView.text = orderSummary
        orderSummaryTextView.visibility = View.VISIBLE
    }
}
