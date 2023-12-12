package com.example.khanjan_bhargav_mapd711_assignment4

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.khanjan_bhargav_mapd711_assignment4.db.AdminRepository
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaDatabase
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaRepository
import com.example.khanjan_bhargav_mapd711_assignment4.model.Admin
import com.example.khanjan_bhargav_mapd711_assignment4.model.Pizza
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.AdminViewModel
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.AdminViewModelFactory
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.PizzaViewModel
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.PizzaViewModelFactory
import kotlinx.coroutines.launch

class AdminAddPizza : AppCompatActivity() {

    private lateinit var viewModel: PizzaViewModel
    private lateinit var pizzaName: EditText
    private lateinit var price: EditText
    private lateinit var addPizzaButton : Button
    private lateinit var addPizzaBackButton : Button

    var selectedItem: String = ""
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_pizza)
        supportActionBar?.hide()

        supportActionBar?.hide()
        val repository =
            PizzaRepository(PizzaDatabase.getDatabaseInstance(applicationContext).PizzaDao())
        val viewModelFactory = PizzaViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[PizzaViewModel::class.java]
        pizzaName = findViewById(R.id.addPizzaName)
        price = findViewById(R.id.addPizzaPrice)

        addPizzaButton = findViewById(R.id.adminAddPizzaButton)
        addPizzaBackButton = findViewById(R.id.adminAddPizzaBackButton)

        val spinner = findViewById<Spinner>(R.id.pizzaTypeSpinner)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Get the selected item from the spinner
                selectedItem = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case where nothing is selected (if needed)
            }
        }

        addPizzaButton.setOnClickListener{
            registerNewProduct ()
        }

        addPizzaBackButton.setOnClickListener{
            finish ()
        }
    }

    fun registerNewProduct() {
        val pizzaNameInput = pizzaName.text.toString().trim()
        val priceInput = price.text.toString().trim()
        val pizzaType = selectedItem.trim()

        var imagePath = ""

        if (pizzaNameInput.isEmpty() || priceInput.isEmpty()
        ) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
        } else {
            // All fields are valid, create a Customer object and proceed to insert into the database
            if(pizzaType == "Chicago Deep-Dish Pizza"){
                imagePath = "deepDishPizza.jpeg"
            }else if(pizzaType == "Sicilian Pizza"){
                imagePath = "SicilianPizza.jpeg"
            }else if(pizzaType == "Stuffed Crust Pizza"){
                imagePath = "StuffedCrustPizza.jpeg"
            }else if(pizzaType == "Margherita Pizza"){
                imagePath = "MargheritaPizza.jpeg"
            }else if(pizzaType == "Vegetarian/Vegan Pizza"){
                imagePath = "VegetarianVeganPizza.jpeg"
            }
                    // All fields are valid, proceed to insert the new customer
                    val newPizza = Pizza(
                        pizzaName = pizzaNameInput,
                        price = priceInput.toInt(),
                        category = pizzaType,
                        image = imagePath
                    )
                    viewModel.insertPizza(newPizza)
//                    finish()
                    Toast.makeText(this@AdminAddPizza, "Successfully Entered Pizza", Toast.LENGTH_SHORT).show()
                }

    }
}