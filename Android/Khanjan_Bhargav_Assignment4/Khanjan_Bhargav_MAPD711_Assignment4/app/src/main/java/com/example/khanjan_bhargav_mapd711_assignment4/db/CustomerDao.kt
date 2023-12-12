package com.example.khanjan_bhargav_mapd711_assignment4.db


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.khanjan_bhargav_mapd711_assignment4.model.Admin
import com.example.khanjan_bhargav_mapd711_assignment4.model.Customer

@Dao
interface CustomerDao {
    @Insert
    suspend fun insertCustomer(customer: Customer)

    @Query("SELECT * FROM customer WHERE userName = :username")
    suspend fun getUserByUsername(username: String): Customer?

    @Query("SELECT * FROM customer WHERE userName = :username AND password = :password")
    suspend fun getUserByUsernameAndPassword(username: String,  password: String): Customer?

    @Query("SELECT * FROM customer WHERE customerId = :customerId")
    suspend fun getUserByUserId(customerId: Int): Customer?

    @Update
    suspend fun updateUser(customer: Customer)



//    @Query("SELECT * FROM customer WHERE id = :id")
//    suspend fun getCustomerById(id: Int): Customer


}