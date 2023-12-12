package com.example.khanjan_bhargav_mapd711_assignment4.db

import com.example.khanjan_bhargav_mapd711_assignment4.model.Admin
import com.example.khanjan_bhargav_mapd711_assignment4.model.Customer

class AdminRepository (
    private val userDao: AdminDao
){

    // Admin
    suspend fun insertAdmin(user: Admin){
        userDao.insertAdmin(user)
    }

    suspend fun isAdminUsernameExists(username: String): Boolean {
        val user = userDao.getAdminByUsername(username)
        return user != null
    }

    suspend fun getUserByUsername(username: String): Admin? {
        return userDao.getAdminByUsername(username)
    }

    suspend fun isAdminUsernameAndPasswordExists(username: String,password:String): Boolean {
        val user = userDao.getAdminByUsernameAndPassword(username,password)
        return user != null
    }

    suspend fun getAdminByUsernameAndPassword(username: String, password: String): Admin? {
        return userDao.getAdminByUsernameAndPassword(username, password)
    }

    suspend fun getUserByUserId(customerId: Int): Admin? {
        return userDao.getUserByUserId(customerId)
    }

    suspend fun updateUser(user: Admin){
        userDao.updateUser(user)
    }
}