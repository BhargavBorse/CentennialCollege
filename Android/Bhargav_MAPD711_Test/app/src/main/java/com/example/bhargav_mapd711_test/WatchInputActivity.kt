// Name: Bhargav Borse
// Id: 301278352
// Mid-Test

package com.example.bhargav_mapd711_test

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class WatchInputActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_input)

        // Initialize views and widgets
        val watchSelectionSpinner = findViewById<Spinner>(R.id.watchSelectionSpinner)
        val loopSelectionGroup = findViewById<RadioGroup>(R.id.loopSelectionGroup)
        val watchSizeGroup = findViewById<RadioGroup>(R.id.watchSizeGroup)
        val tradeOptionGroup = findViewById<RadioGroup>(R.id.tradeOptionGroup)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("WatchAppPreferences", MODE_PRIVATE)

        // Populate the Spinner with Apple Watch options
        val watchOptions = arrayOf(
            "Apple Watch Series 9 - $550",
            "Apple Watch Ultra 2 - $1100",
            "Apple Watch SE - $300",
            "Apple Watch Nike - $400",
            "Apple Watch Hermès - $1700"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, watchOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        watchSelectionSpinner.adapter = adapter

        // Handle the Calculate button click
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener {
            // Retrieve user selections
            val selectedWatch = watchSelectionSpinner.selectedItem.toString()
            val selectedLoop = findViewById<RadioButton>(
                loopSelectionGroup.checkedRadioButtonId
            ).text.toString()
            val selectedSize = findViewById<RadioButton>(
                watchSizeGroup.checkedRadioButtonId
            ).text.toString()
            val tradeOption = findViewById<RadioButton>(
                tradeOptionGroup.checkedRadioButtonId
            ).text.toString()

            // Calculate the total price
            var totalPrice = when (selectedWatch) {
                "Apple Watch Series 9 - $550" -> 550
                "Apple Watch Ultra 2 - $1100" -> 1100
                "Apple Watch SE - $300" -> 300
                "Apple Watch Nike - $400" -> 400
                "Apple Watch Hermès - $1700" -> 1700
                else -> 0
            }

            when (selectedLoop) {
                "Nike Sports Loop - $50" -> totalPrice += 50
                "Bridon Single Tour - $60" -> totalPrice += 60
                "Trail Loop - $70" -> totalPrice += 70
            }

            when (selectedSize) {
                "41 inches - $50" -> totalPrice += 50
                "45 inches - $100" -> totalPrice += 100
            }

            if (tradeOption == "Trade-In") {
                totalPrice -= 50
            }

            // Pass the total price to WatchResultActivity
            val intent = Intent(this, WatchResultActivity::class.java)
            intent.putExtra("totalPrice", totalPrice)
            startActivity(intent)
        }
    }
}
