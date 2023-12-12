package com.example.khanjan_bhargav_mapd711_assignment4

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.khanjan_bhargav_mapd711_assignment4.db.CustomerRepository
import com.example.khanjan_bhargav_mapd711_assignment4.db.OrdersRepository
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaDatabase
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.CustomerViewModel
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.CustomerViewModelFactory
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.OrderViewModelFactory
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.ordersViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class customerViewAllOrders : AppCompatActivity() {
    private lateinit var viewModel: ordersViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var backButton: ImageButton
    private lateinit var sharedPreferences: SharedPreferences
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_view_all_orders)

        supportActionBar?.hide()
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        recyclerView = findViewById(R.id.recyclerViewCustomerProducts)
        backButton = findViewById(R.id.customerViewAllProductsBackButton)

        val backImageStream = assets.open("left-arrow.png")
        val bitmap = BitmapFactory.decodeStream(backImageStream)
        backButton.setImageBitmap(bitmap)

        backButton.setOnClickListener{
            finish()
        }

        val repository =
            OrdersRepository(PizzaDatabase.getDatabaseInstance(applicationContext).OrdersDao())
        val viewModelFactory = OrderViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ordersViewModel::class.java]

        loadProducts()

    }

    private fun initRecyclerview() {
        recyclerView.layoutManager = LinearLayoutManager(this@customerViewAllOrders)
    }
    private fun loadProducts() {
        CoroutineScope(Dispatchers.IO).launch {
//            val products = viewModel.getAllProduct()
            val customerId = sharedPreferences.getInt("userId", 0)

            val products = viewModel.getAllProductsForParticularUser(customerId)
            lifecycleScope.launch(Dispatchers.Main) {
                Toast.makeText(
                    this@customerViewAllOrders,
                    "Total Size ${products?.size}",
                    Toast.LENGTH_LONG
                ).show()

                initRecyclerview()
                val adapter = products?.let { CustomerOrderAdaptor(it) }
                recyclerView.adapter = adapter

            }
        }
    }
}