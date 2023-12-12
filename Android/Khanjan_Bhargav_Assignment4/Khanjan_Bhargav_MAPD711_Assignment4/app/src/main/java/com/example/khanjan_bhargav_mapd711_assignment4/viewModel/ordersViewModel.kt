package com.example.khanjan_bhargav_mapd711_assignment4.viewModel


import com.example.khanjan_bhargav_mapd711_assignment4.db.AdminRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khanjan_bhargav_mapd711_assignment4.db.OrdersRepository
import com.example.khanjan_bhargav_mapd711_assignment4.model.Orders
import kotlinx.coroutines.launch


class ordersViewModel (
    private val orderRepository: OrdersRepository
): ViewModel() {
//    private val _users = MutableLiveData<List<User>>()
//    val users: LiveData<List<User>>
//        get() = _users
//
//    init {
//        getAllUsers()
//    }

    private val _orders = MutableLiveData<List<Orders>>()
    val users: LiveData<List<Orders>>
        get() = _orders

    // admin
    fun insertOrder(product: Orders) {
        viewModelScope.launch {
            orderRepository.insertProduct(product)
        }
    }

    fun updateOrder(product: Orders) {
        viewModelScope.launch {
            orderRepository.updateProduct(product)
        }
    }

    suspend fun getAllProductsForParticularUser(customerId: Int): List<Orders>?{
        return orderRepository.getAllProductsForParticularUser(customerId)
    }


//    fun getAllProduct() {
//        viewModelScope.launch {
//            orderRepository.getAllProduct()
//        }
//    }

    suspend fun getAllProduct(): List<Orders>? {
        return orderRepository.getAllProduct()
//        viewModelScope.launch {
//            val result = userRepository.getAllUsers()
////            _users.value = result
//        }
    }


}