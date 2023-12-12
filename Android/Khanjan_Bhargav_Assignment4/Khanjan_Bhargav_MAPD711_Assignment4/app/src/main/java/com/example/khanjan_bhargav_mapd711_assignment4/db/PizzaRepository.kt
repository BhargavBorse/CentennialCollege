package com.example.khanjan_bhargav_mapd711_assignment4.db

import com.example.khanjan_bhargav_mapd711_assignment4.model.Customer
import com.example.khanjan_bhargav_mapd711_assignment4.model.Pizza

class PizzaRepository (
    private val productDao: PizzaDao
){

    suspend fun insertProduct(pizza: Pizza){
        productDao.insertProduct(pizza)
    }
    suspend fun getAllProduct(): List<Pizza>? {
        return productDao.getAllProducts()
    }

    suspend fun getAllProductbyId(id:Int): Pizza? {
        return productDao.getProductById(id)
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