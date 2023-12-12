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

class AdminHomePage : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userInfo: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home_page)
        supportActionBar?.hide()

        val profileImage = findViewById<ImageButton>(R.id.adminProfileButton)
        val logoutImage = findViewById<ImageButton>(R.id.adminLogoutButton)

        val addNewPizzaLinearLayout = findViewById<LinearLayout>(R.id.AddNewPizzaLinearLayout)

        val addNewPizzaImage = findViewById<ImageView>(R.id.adminAddPizzaImage)
        val managePizzaImage = findViewById<ImageView>(R.id.adminManageOrderImage)

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        userInfo = findViewById(R.id.adminHomepageHello)


        userInfo.text =  sharedPreferences.getString("username", "")


        // Load the image from the assets folder
        val profileImageStream = assets.open("user.png")
        val logoutImageStream = assets.open("logout.png")

        val addPizzaImageStream = assets.open("adddNewPizza.jpeg")
        val managePizzaImageStream = assets.open("managePizzaOrders.png")

        // Decode the input stream into a Bitmap and set it to the ImageView
        val bitmap = BitmapFactory.decodeStream(profileImageStream)
        profileImage.setImageBitmap(bitmap)

        val bitmap1 = BitmapFactory.decodeStream(logoutImageStream)
        logoutImage.setImageBitmap(bitmap1)

        val bitmap2 = BitmapFactory.decodeStream(managePizzaImageStream)
        managePizzaImage.setImageBitmap(bitmap2)

        val bitmap3 = BitmapFactory.decodeStream(addPizzaImageStream)
        addNewPizzaImage.setImageBitmap(bitmap3)


        addNewPizzaLinearLayout.setOnClickListener{
            val intent = Intent(this,AdminAddPizza::class.java)
            startActivity(intent)
        }

        logoutImage.setOnClickListener{
            sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(this,adminLogin::class.java)
            startActivity(intent)
            finish()
        }

        profileImage.setOnClickListener{
            val intent = Intent(this,AdminProfile::class.java)
            startActivity(intent)
        }

        managePizzaImage.setOnClickListener{
            val intent = Intent(this,AdminViewAllProducts::class.java)
            startActivity(intent)
        }

    }
}