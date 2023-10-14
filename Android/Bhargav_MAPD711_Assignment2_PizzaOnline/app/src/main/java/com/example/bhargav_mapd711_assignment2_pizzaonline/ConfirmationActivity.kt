//Made by: Bhargav Borse
//ID: 301278352
//Assignment-2
//Date of Submission: 13/10/2023

package com.example.bhargav_mapd711_assignment2_pizzaonline

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var orderSummaryTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        // Initializing SharedPreferences for storing order information
        sharedPreferences = getSharedPreferences("OrderPrefs", Context.MODE_PRIVATE)

        // Initializing the TextView for displaying the order summary
        orderSummaryTextView = findViewById(R.id.orderSummaryTextView)

        // Retrieving and displaying the order summary information from SharedPreferences
        val customerName = sharedPreferences.getString("customerName", "")
        val selectedPizzaType = sharedPreferences.getString("selectedPizzaType", "")
        val selectedSize = sharedPreferences.getString("selectedSize", "")
        val selectedToppings = sharedPreferences.getStringSet("selectedToppings", emptySet())?.joinToString(", ")
        val customerAddress = sharedPreferences.getString("customerAddress", "")

        // Preparing the text to display in the order summary
        val toppingsText = if (selectedToppings.isNullOrEmpty()) {
            "No toppings"
        } else {
            "Toppings: $selectedToppings"
        }

        val orderSummary = """
            Customer Name: $customerName
            Type of Pizza: $selectedPizzaType
            Size of Pizza: $selectedSize
            $toppingsText
            Customer Address: $customerAddress
            
            Thank you for your online order. Your order has been successfully received and will be delivered soon.
        """.trimIndent()

        // Setting the order summary text in the TextView
        orderSummaryTextView.text = orderSummary
    }
}
