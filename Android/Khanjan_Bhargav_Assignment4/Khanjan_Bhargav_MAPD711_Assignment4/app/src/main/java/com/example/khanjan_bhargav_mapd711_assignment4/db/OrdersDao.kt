package com.example.khanjan_bhargav_mapd711_assignment4.db


import androidx.room.Dao
import androidx.room.Index
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.khanjan_bhargav_mapd711_assignment4.model.Customer
import com.example.khanjan_bhargav_mapd711_assignment4.model.Orders
import com.example.khanjan_bhargav_mapd711_assignment4.model.Pizza

@Dao
interface OrdersDao {
    @Insert
    suspend fun insertProduct(order: Orders)

    @Query("SELECT * FROM orders")
    suspend fun getAllProducts(): List<Orders>?

    @Update
    suspend fun updateOrder(order: Orders)

    @Query("SELECT * FROM orders WHERE customerId = :customerId")
    suspend fun getAllProductsForParticularUser(customerId:Int): List<Orders>?


//    @Query("SELECT * FROM customer WHERE id = :id")
//    suspend fun getCustomerById(id: Int): Customer


}