package com.example.khanjan_bhargav_mapd711_assignment4

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.khanjan_bhargav_mapd711_assignment4.db.CustomerRepository
import com.example.khanjan_bhargav_mapd711_assignment4.db.OrdersRepository
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaDatabase
import com.example.khanjan_bhargav_mapd711_assignment4.model.Customer
import com.example.khanjan_bhargav_mapd711_assignment4.model.Orders
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.CustomerViewModel
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.CustomerViewModelFactory
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.OrderViewModelFactory
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.ordersViewModel
import kotlinx.coroutines.launch

class ConfirmChangeStatus : AppCompatActivity() {

    private lateinit var viewModel: ordersViewModel

    private lateinit var productName: TextView
    private lateinit var qty: TextView
    private lateinit var image: ImageView
    private lateinit var orderDate: TextView
    private lateinit var status: TextView
    private lateinit var changeButton: Button

    private lateinit var backButton: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_change_status)

        supportActionBar?.hide()
        productName = findViewById(R.id.adminChangeStatusProductName)
        qty = findViewById(R.id.adminChangeStatusProductQty)
        image = findViewById(R.id.adminChangeStatusImage)
        orderDate = findViewById(R.id.adminChangeStatusOrderDate)
        status = findViewById(R.id.adminChangeStatusOrderStatus)
        changeButton = findViewById(R.id.adminChangeStatusButton)

        backButton = findViewById(R.id.adminChangeStatusBackButton)

        val backImageStream = assets.open("left-arrow.png")
        val bitmap = BitmapFactory.decodeStream(backImageStream)
        backButton.setImageBitmap(bitmap)

        backButton.setOnClickListener{
            finish()
        }

        productName.text = intent.getStringExtra("productName")
        qty.text = intent.getStringExtra("productQty")

        var qty1 : Int = 0
        qty1 = (qty.text as String?)!!.toInt()

        var orderId11: Int = 0
        val orderId1 = intent.getStringExtra("orderId")
        orderId11 = orderId1!!.toInt()

        var customerId11: Int = 0
        val customerId1 = intent.getStringExtra("customerId")
        customerId11 = customerId1!!.toInt()

        var productIdId11: Int = 0
        val productIdId1 = intent.getStringExtra("productId")
        productIdId11 = productIdId1!!.toInt()

        val assetImagePath = intent.getStringExtra("productImage")
        Glide.with(this)
            .load(assetImagePath)
            .into(image)// Se

        orderDate.text = intent.getStringExtra("orderDate")
        status.text = intent.getStringExtra("orderStatus")

        val repository =
            OrdersRepository(PizzaDatabase.getDatabaseInstance(applicationContext).OrdersDao())
        val viewModelFactory = OrderViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ordersViewModel::class.java]


        changeButton.setOnClickListener{
            viewModel.viewModelScope.launch {

                // All fields are valid, proceed to insert the new customer
                val newOrder = Orders(
                    orderId = orderId11,
                    productId = productIdId11,
                    orderDate = orderDate.text.toString(),
                    qty = qty1,
                    orderStatus = "Delivered",
                    customerId =customerId11,
                    productName = productName.text.toString(),
                    image = assetImagePath.toString()
                )
                viewModel.updateOrder(newOrder)
                finish()
                val intent = Intent(this@ConfirmChangeStatus,AdminViewAllProducts::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent)
                Toast.makeText(this@ConfirmChangeStatus, "Successfully Updated", Toast.LENGTH_SHORT).show()

            }
        }

    }
}