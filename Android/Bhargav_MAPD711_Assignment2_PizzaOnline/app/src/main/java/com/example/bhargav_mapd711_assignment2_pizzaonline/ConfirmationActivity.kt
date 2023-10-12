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

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("OrderPrefs", Context.MODE_PRIVATE)

        orderSummaryTextView = findViewById(R.id.orderSummaryTextView)

        // Retrieve and display the order summary from SharedPreferences
        val customerName = sharedPreferences.getString("customerName", "")
        val selectedPizzaType = sharedPreferences.getString("selectedPizzaType", "")
        val selectedSize = sharedPreferences.getString("selectedSize", "")
        val selectedToppings = sharedPreferences.getStringSet("selectedToppings", emptySet())?.joinToString(", ")
        val customerAddress = sharedPreferences.getString("customerAddress", "")

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
            Thank you for your order!
        """.trimIndent()

        orderSummaryTextView.text = orderSummary
    }
}

