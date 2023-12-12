// Team: Bhargav Borse - 301278352 and Khanjan Dave - 301307330
// Assignment 3
// File: CityDetailsActivity.kt
// Description: This activity is part of an Android app designed to simplify the search for pizza restaurants. Users can choose from a list of cities, including Toronto, Scarborough, Mississauga, Oakville, and North York, and a few personal favorites from different regions. This activity displays the details of the selected city and uses Google Maps to show markers for pizza restaurants in that city. It makes it easy for users to find and enjoy delicious pizza wherever they are.

package com.example.bhargav_khanjan_assignment3

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bhargav_khanjan_assignment3.R
import com.google.android.gms.maps.SupportMapFragment

class CityDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_details)

        // Initialize SharedPreferences to retrieve the selected city
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val selectedCity = sharedPreferences.getString("selectedCity", "Toronto")

        // Set the text of the cityHeading TextView to the selected city name.
        val cityHeading = findViewById<TextView>(R.id.cityHeading)
        cityHeading.text = selectedCity

        // Initialize Google Maps fragment to show pizza restaurant locations.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            // Use Google Maps API to show markers for pizza restaurants in the selected city.
            // You can customize the map, add markers, and implement your innovative feature here.
        }
    }
}