package com.example.khanjan_bhargav_mapd711_assignment4.db

import com.example.khanjan_bhargav_mapd711_assignment4.model.Admin
import com.example.khanjan_bhargav_mapd711_assignment4.model.Customer

class CustomerRepository (
    private val userDao: CustomerDao
){

    suspend fun insertUser(user: Customer){
        userDao.insertCustomer(user)
    }

    suspend fun isUsernameExists(username: String): Boolean {
        val user = userDao.getUserByUsername(username)
        return user != null
    }

    suspend fun isUsernameAndPasswordExists(username: String,password:String): Boolean {
        val user = userDao.getUserByUsernameAndPassword(username,password)
        return user != null
    }

    suspend fun getUserByUsername(username: String): Customer? {
        return userDao.getUserByUsername(username)
    }

    suspend fun getUserByUserId(customerId: Int): Customer? {
        return userDao.getUserByUserId(customerId)
    }

    suspend fun updateUser(user: Customer){
        userDao.updateUser(user)
    }


//    suspend fun getAllUsers(): List<Customer>{
//        return userDao.getAllUsers()
//    }
//
//    suspend fun updateUser(user: Customer) {
//        userDao.updateUser(user)
//    }
//
//    suspend fun deleteUser(user: Customer) {
//        userDao.deleteUser(user)
//    }
//
//    suspend fun deleteAll() {
//        userDao.deleteAll()
//    }

}