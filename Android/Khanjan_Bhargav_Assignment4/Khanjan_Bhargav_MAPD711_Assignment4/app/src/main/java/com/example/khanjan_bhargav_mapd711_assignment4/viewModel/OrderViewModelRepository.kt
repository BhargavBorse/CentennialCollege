package com.example.khanjan_bhargav_mapd711_assignment4.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.khanjan_bhargav_mapd711_assignment4.db.OrdersRepository

class OrderViewModelFactory (private val repository: OrdersRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ordersViewModel::class.java)) {
            return ordersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}