package com.example.khanjan_bhargav_mapd711_assignment4.db

import com.example.khanjan_bhargav_mapd711_assignment4.model.Orders


class OrdersRepository (
    private val orderDao: OrdersDao
){

    suspend fun insertProduct(pizza: Orders){
        orderDao.insertProduct(pizza)
    }
    suspend fun getAllProduct(): List<Orders>? {
        return orderDao.getAllProducts()
    }
    suspend fun updateProduct(pizza: Orders){
        orderDao.updateOrder(pizza)
    }
    suspend fun getAllProductsForParticularUser(customerId : Int): List<Orders>? {
        return orderDao.getAllProductsForParticularUser(customerId)
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