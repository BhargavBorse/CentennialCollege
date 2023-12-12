package com.example.khanjan_bhargav_mapd711_assignment4

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaDatabase
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaRepository
import com.example.khanjan_bhargav_mapd711_assignment4.model.Pizza
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.PizzaViewModel
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.PizzaViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class customerHomePage : AppCompatActivity() {
    private lateinit var viewModel: PizzaViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var adapter: PizzaAdaptor
    private lateinit var recyclerView: RecyclerView

    private lateinit var userInfo:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_home_page)

        supportActionBar?.hide()
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        val profileImage = findViewById<ImageButton>(R.id.customerProfileButton)
        val logoutImage = findViewById<ImageButton>(R.id.customerLogoutButton)
        val previousOrders = findViewById<ImageButton>(R.id.customerPreviousOrders)
        userInfo = findViewById(R.id.customerHomepageHello)

        recyclerView = findViewById(R.id.recyclerView)

        // Load the image from the assets folder
        val profileImageStream = assets.open("user.png")
        val logoutImageStream = assets.open("logout.png")
        val customerPreviousOrders = assets.open("shopping-list.png")



        // Decode the input stream into a Bitmap and set it to the ImageView
        val bitmap = BitmapFactory.decodeStream(profileImageStream)
        profileImage.setImageBitmap(bitmap)

        val bitmap1 = BitmapFactory.decodeStream(logoutImageStream)
        logoutImage.setImageBitmap(bitmap1)

        val bitmap2 = BitmapFactory.decodeStream(customerPreviousOrders)
        previousOrders.setImageBitmap(bitmap2)


        logoutImage.setOnClickListener{
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(this,CustomerLogin::class.java)
            startActivity(intent)
            finish()
        }

        profileImage.setOnClickListener(){
            val intent = Intent(this,CustomerProfile::class.java)
            startActivity(intent)
        }

        previousOrders.setOnClickListener{
            val intent = Intent(this,customerViewAllOrders::class.java)
            startActivity(intent)
        }

        val repository =
            PizzaRepository(PizzaDatabase.getDatabaseInstance(applicationContext).PizzaDao())
        val viewModelFactory = PizzaViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[PizzaViewModel::class.java]


        userInfo.text =  sharedPreferences.getString("username", "")

        loadUsers()

    }
    private fun initRecyclerview() {
        recyclerView.layoutManager = LinearLayoutManager(this@customerHomePage)
    }
    private fun loadUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            val products = viewModel.getAllProduct()
            lifecycleScope.launch(Dispatchers.Main) {
                Toast.makeText(
                    this@customerHomePage,
                    "Total Size ${products?.size}",
                    Toast.LENGTH_LONG
                ).show()

                initRecyclerview()
                val adapter = products?.let { PizzaAdaptor(it) }
                recyclerView.adapter = adapter

            }
        }
    }
}