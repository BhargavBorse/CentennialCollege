package com.example.khanjan_bhargav_mapd711_assignment4.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khanjan_bhargav_mapd711_assignment4.db.CustomerRepository
import com.example.khanjan_bhargav_mapd711_assignment4.model.Admin
import kotlinx.coroutines.launch
import com.example.khanjan_bhargav_mapd711_assignment4.model.Customer


class CustomerViewModel (
    private val userRepository: CustomerRepository
): ViewModel() {
//    private val _users = MutableLiveData<List<User>>()
//    val users: LiveData<List<User>>
//        get() = _users
//
//    init {
//        getAllUsers()
//    }

    private val _users = MutableLiveData<List<Customer>>()
    val users: LiveData<List<Customer>>
        get() = _users

//    init {
//        getUsers()
//    }

//    private fun getUsers() {
//        viewModelScope.launch {
//            val result = userRepository.getAllUsers()
//            _users.value = result
//        }
//    }

//    suspend fun getAllUsers(): List<Customer> {
//        return userRepository.getAllUsers()
////        viewModelScope.launch {
////            val result = userRepository.getAllUsers()
//////            _users.value = result
////        }
//    }

    fun insertUser(user: Customer) {
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }

    suspend fun isUsernameExists(username: String): Boolean {
        return userRepository.isUsernameExists(username)
    }

    suspend fun isUsernameAndPasswordExists(username: String,password:String): Boolean {
        return userRepository.isUsernameAndPasswordExists(username,password)
    }

    suspend fun getUserByUsername(username: String): Customer? {
        return userRepository.getUserByUsername(username)
    }

    suspend fun getUserByUserId(customerId: Int): Customer? {
        return userRepository.getUserByUserId(customerId)
    }

    fun updateUser(user: Customer) {
        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }


//    fun updateUser(user: Customer) {
//        viewModelScope.launch {
//            userRepository.updateUser(user)
//        }
//    }

//    fun deleteUser(user: Customer) {
//        viewModelScope.launch {
//            userRepository.deleteUser(user)
//        }
//    }

//    fun deleteAll() {
//        viewModelScope.launch {
//            userRepository.deleteAll()
//        }
//    }

}