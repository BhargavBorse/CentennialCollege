package com.example.khanjan_bhargav_mapd711_assignment4.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "pizza")
data class Pizza(
    @PrimaryKey(autoGenerate = true)
    val productId: Int = 0,
    val pizzaName: String,
    val price: Int,
    val category: String,
    val image: String,

    ) {
    @get:Ignore
    val productId1: String
        get() = "pizza$productId"
}