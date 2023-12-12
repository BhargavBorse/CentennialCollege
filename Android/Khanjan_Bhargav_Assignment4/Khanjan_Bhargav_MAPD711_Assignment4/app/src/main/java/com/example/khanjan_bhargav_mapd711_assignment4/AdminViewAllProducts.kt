package com.example.khanjan_bhargav_mapd711_assignment4

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.khanjan_bhargav_mapd711_assignment4.db.OrdersRepository
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaDatabase
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaRepository
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.OrderViewModelFactory
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.PizzaViewModel
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.PizzaViewModelFactory
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.ordersViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdminViewAllProducts : AppCompatActivity() {
    private lateinit var viewModel: ordersViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var backButton: ImageButton
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_view_all_products)

        supportActionBar?.hide()

        recyclerView = findViewById(R.id.recyclerViewAdminProducts)
        backButton = findViewById(R.id.adminViewAllProductsBackButton)

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
        recyclerView.layoutManager = LinearLayoutManager(this@AdminViewAllProducts)
    }
    private fun loadProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            val products = viewModel.getAllProduct()
            lifecycleScope.launch(Dispatchers.Main) {
                Toast.makeText(
                    this@AdminViewAllProducts,
                    "Total Size ${products?.size}",
                    Toast.LENGTH_LONG
                ).show()

                initRecyclerview()
                val adapter = products?.let { OrderAdaptor(it) }
                recyclerView.adapter = adapter

            }
        }
    }
}