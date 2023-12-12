package com.example.khanjan_bhargav_mapd711_assignment4.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaRepository

class PizzaViewModelFactory (private val repository: PizzaRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PizzaViewModel::class.java)) {
            return PizzaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}