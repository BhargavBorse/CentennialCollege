package com.example.khanjan_bhargav_mapd711_assignment4.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.khanjan_bhargav_mapd711_assignment4.model.Admin
import com.example.khanjan_bhargav_mapd711_assignment4.model.Customer

@Dao
interface AdminDao {

    // Admin

    @Insert
    suspend fun insertAdmin(admin: Admin)

    @Query("SELECT * FROM admin WHERE userName = :username")
    suspend fun getAdminByUsername(username: String): Admin?

    @Query("SELECT * FROM admin WHERE userName = :username AND password = :password")
    suspend fun getAdminByUsernameAndPassword(username: String,  password: String): Admin?

    @Query("SELECT * FROM admin WHERE employeeId = :customerId")
    suspend fun getUserByUserId(customerId: Int): Admin?

    @Update
    suspend fun updateUser(customer: Admin)




}