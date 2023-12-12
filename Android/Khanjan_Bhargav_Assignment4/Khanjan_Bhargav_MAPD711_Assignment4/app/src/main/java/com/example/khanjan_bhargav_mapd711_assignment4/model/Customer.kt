package com.example.khanjan_bhargav_mapd711_assignment4.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val customerId: Int = 0,
    val userName: String,
    val password: String,
    val firstname: String,
    val lastname: String,
    val address: String,
    val city: String,
    val postalCode: String
) {
    @get:Ignore
    val customerId1: String
        get() = "customer$customerId"
}