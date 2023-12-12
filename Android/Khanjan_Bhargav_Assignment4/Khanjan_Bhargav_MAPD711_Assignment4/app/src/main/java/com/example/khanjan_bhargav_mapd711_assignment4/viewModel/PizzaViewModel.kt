package com.example.khanjan_bhargav_mapd711_assignment4.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaRepository
import com.example.khanjan_bhargav_mapd711_assignment4.model.Pizza
import kotlinx.coroutines.launch


class PizzaViewModel (
    private val pizzaRepository: PizzaRepository
): ViewModel() {
//    private val _users = MutableLiveData<List<User>>()
//    val users: LiveData<List<User>>
//        get() = _users
//
//    init {
//        getAllUsers()
//    }

    private val _users = MutableLiveData<List<Pizza>>()
    val users: LiveData<List<Pizza>>
        get() = _users

    // admin
    fun insertPizza(pizza: Pizza) {
        viewModelScope.launch {
            pizzaRepository.insertProduct(pizza)
        }
    }
    suspend fun getAllProduct() : List<Pizza>?{
            return pizzaRepository.getAllProduct()
    }

    suspend fun getAllProductById(id:Int) : Pizza?{
        return pizzaRepository.getAllProductbyId(id)
    }



}