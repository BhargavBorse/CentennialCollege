package com.example.khanjan_bhargav_mapd711_assignment4.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.khanjan_bhargav_mapd711_assignment4.db.CustomerRepository

class CustomerViewModelFactory (private val repository: CustomerRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomerViewModel::class.java)) {
            return CustomerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}