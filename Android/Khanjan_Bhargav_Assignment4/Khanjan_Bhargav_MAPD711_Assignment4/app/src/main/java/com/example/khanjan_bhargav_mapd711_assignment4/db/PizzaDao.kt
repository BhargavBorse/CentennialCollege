package com.example.khanjan_bhargav_mapd711_assignment4.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.khanjan_bhargav_mapd711_assignment4.model.Pizza

@Dao
interface PizzaDao {
    @Insert
    suspend fun insertProduct(pizza: Pizza)

    @Query("SELECT * FROM pizza")
    suspend fun getAllProducts(): List<Pizza>?


    @Query("SELECT * FROM pizza WHERE productId = :id")
    suspend fun getProductById(id: Int): Pizza


}