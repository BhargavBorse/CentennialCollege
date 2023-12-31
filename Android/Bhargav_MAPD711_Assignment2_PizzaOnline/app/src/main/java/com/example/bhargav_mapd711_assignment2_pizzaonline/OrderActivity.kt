//Made by: Bhargav Borse
//ID: 301278352
//Assignment-2
//Date of Submission: 13/10/2023

package com.example.bhargav_mapd711_assignment2_pizzaonline

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var pizzaSizeRadioGroup: RadioGroup
    private lateinit var checkoutButton: Button

    private lateinit var cheeseCheckbox: CheckBox
    private lateinit var greenPepperCheckbox: CheckBox
    private lateinit var smokedHamCheckbox: CheckBox
    private lateinit var spinachCheckbox: CheckBox
    private lateinit var blackOlivesCheckbox: CheckBox
    private lateinit var spanishOnionsCheckbox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // Initializing SharedPreferences for storing order preferences
        sharedPreferences = getSharedPreferences("OrderPrefs", MODE_PRIVATE)

        pizzaSizeRadioGroup = findViewById(R.id.pizzaSizeRadioGroup)
        checkoutButton = findViewById(R.id.checkoutButton)

        cheeseCheckbox = findViewById(R.id.cheeseCheckbox)
        greenPepperCheckbox = findViewById(R.id.greenPepperCheckbox)
        smokedHamCheckbox = findViewById(R.id.hamCheckbox)
        spinachCheckbox = findViewById(R.id.spinachCheckbox)
        blackOlivesCheckbox = findViewById(R.id.olivesCheckbox)
        spanishOnionsCheckbox = findViewById(R.id.onionsCheckbox)

        checkoutButton.setOnClickListener {
            if (validateOrder()) {
                // Collecting data from UI components
                val selectedSize = getSelectedSize()
                val selectedToppings = getSelectedToppings()

                // Saving the selected size and toppings to SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("selectedSize", selectedSize)
                editor.putStringSet("selectedToppings", selectedToppings.toSet())
                editor.apply()

                // Navigating to the customer information activity
                val intent = Intent(this, CustomerInfoActivity::class.java)
                startActivity(intent)
            }
        }
    }

    // Helper method to get the selected pizza size
    private fun getSelectedSize(): String {
        val checkedRadioButtonId = pizzaSizeRadioGroup.checkedRadioButtonId
        return findViewById<RadioButton>(checkedRadioButtonId).text.toString()
    }

    // Helper method to get selected toppings
    private fun getSelectedToppings(): ArrayList<String> {
        val selectedToppings = ArrayList<String>()
        if (cheeseCheckbox.isChecked) selectedToppings.add(cheeseCheckbox.text.toString())
        if (greenPepperCheckbox.isChecked) selectedToppings.add(greenPepperCheckbox.text.toString())
        if (smokedHamCheckbox.isChecked) selectedToppings.add(smokedHamCheckbox.text.toString())
        if (spinachCheckbox.isChecked) selectedToppings.add(spinachCheckbox.text.toString())
        if (blackOlivesCheckbox.isChecked) selectedToppings.add(blackOlivesCheckbox.text.toString())
        if (spanishOnionsCheckbox.isChecked) selectedToppings.add(spanishOnionsCheckbox.text.toString())

        return selectedToppings
    }

    // Helper method to validate the order
    private fun validateOrder(): Boolean {
        if (pizzaSizeRadioGroup.checkedRadioButtonId == -1) {
            showToast("Please select a pizza size.")
            return false
        }

        val selectedToppings = getSelectedToppings()
        if (selectedToppings.isEmpty()) {
            showToast("Please select at least one topping.")
            return false
        }

        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
