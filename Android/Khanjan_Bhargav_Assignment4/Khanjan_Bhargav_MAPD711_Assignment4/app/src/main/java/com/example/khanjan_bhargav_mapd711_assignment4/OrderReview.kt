package com.example.khanjan_bhargav_mapd711_assignment4

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.khanjan_bhargav_mapd711_assignment4.db.AdminRepository
import com.example.khanjan_bhargav_mapd711_assignment4.db.OrdersRepository
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaDatabase
import com.example.khanjan_bhargav_mapd711_assignment4.model.Admin
import com.example.khanjan_bhargav_mapd711_assignment4.model.Orders
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.AdminViewModel
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.AdminViewModelFactory
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.OrderViewModelFactory
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.PizzaViewModel
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.ordersViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class OrderReview : AppCompatActivity() {

    private lateinit var viewModel: ordersViewModel
    private lateinit var productName : TextView
    private lateinit var productQty : TextView
    private lateinit var productPrice : TextView
    private lateinit var productTotalPrice : TextView
    private lateinit var productImage : ImageView
    private lateinit var confirmOrder: Button
    private lateinit var backButton: ImageButton
   private var productId :Int = 0

    private lateinit var sharedPreferences: SharedPreferences
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_review)
        supportActionBar?.hide()

        productName = findViewById(R.id.reviewProductName)
        productQty = findViewById(R.id.reviewProductQty)
        productPrice = findViewById(R.id.reviewProductPrice)
        productTotalPrice = findViewById(R.id.reviewTotalPrice)
        productImage = findViewById(R.id.reviewProductImage)
        confirmOrder = findViewById(R.id.reviewConfirmOrder)
        backButton = findViewById(R.id.reviewBackButton)
        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        val customerId = sharedPreferences.getInt("userId", 0)

        val repository =
            OrdersRepository(PizzaDatabase.getDatabaseInstance(applicationContext).OrdersDao())
        val viewModelFactory = OrderViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ordersViewModel::class.java]

        val backImageStream = assets.open("left-arrow.png")
        val bitmap = BitmapFactory.decodeStream(backImageStream)
        backButton.setImageBitmap(bitmap)
        backButton.setOnClickListener{
            finish()
        }

        val priceIntent = intent.getStringExtra("productPrice")
        val totalPriceIntent = intent.getStringExtra("productTotalPrice")

        productName.text = intent.getStringExtra("productName")
        productQty.text = intent.getStringExtra("productQty")
        productPrice.text = "$ ${priceIntent}"
        productTotalPrice.text = "$ ${totalPriceIntent}"
        val assetImagePath = intent.getStringExtra("productImage")
        Glide.with(this)
            .load(assetImagePath)
            .into(productImage)

        val getProductId = intent.getStringExtra("productId")

        var qty : Int = 0
        qty = (productQty.text as String?)!!.toInt()
        if (getProductId != null) {
            productId = getProductId.toInt()
        }

        confirmOrder.setOnClickListener{
            viewModel.viewModelScope.launch {

                val currentDateTime = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

                val formattedDateTime = currentDateTime.format(formatter)

                    // All fields are valid, proceed to insert the new customer
                    val newOrder = Orders(
                        productId = productId,
                        orderDate = formattedDateTime,
                        qty = qty,
                        orderStatus = "In-Process",
                        customerId =customerId,
                        productName = productName.text.toString(),
                        image = assetImagePath.toString()
                    )
                    viewModel.insertOrder(newOrder)
                    finish()
                    Toast.makeText(this@OrderReview, "Successfully Registered", Toast.LENGTH_SHORT).show()

            }
        }
    }
}