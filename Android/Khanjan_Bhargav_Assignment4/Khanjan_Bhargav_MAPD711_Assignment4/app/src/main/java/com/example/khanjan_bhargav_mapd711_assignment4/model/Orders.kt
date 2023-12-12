package com.example.khanjan_bhargav_mapd711_assignment4.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Orders(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int = 0,
    val customerId: Int,
    val productId: Int,
    val orderDate: String,
    val qty: Int,
    val orderStatus: String,
    val image: String,
    val productName:String

) {
    @get:Ignore
    val orderId1: String
        get() = "order$orderId"
}