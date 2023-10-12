package com.example.bhargav_mapd711_assignment2_pizzaonline

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("OrderPrefs", MODE_PRIVATE)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.pizza_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.meatSupreme -> {
                saveSelectedPizza("Meat Supreme")
                return true
            }
            R.id.superHawaiian -> {
                saveSelectedPizza("Super Hawaiian")
                return true
            }
            R.id.veggie -> {
                saveSelectedPizza("Veggie")
                return true
            }
            R.id.mediterranean -> {
                saveSelectedPizza("Mediterranean")
                return true
            }
            R.id.cheddarSupreme -> {
                saveSelectedPizza("Cheddar Supreme")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun saveSelectedPizza(pizzaType: String) {
        val editor = sharedPreferences.edit()
        editor.putString("selectedPizzaType", pizzaType)
        editor.apply()

        val intent = Intent(this, OrderActivity::class.java)
        intent.putExtra("pizzaType", pizzaType)
        startActivity(intent)
    }
}
