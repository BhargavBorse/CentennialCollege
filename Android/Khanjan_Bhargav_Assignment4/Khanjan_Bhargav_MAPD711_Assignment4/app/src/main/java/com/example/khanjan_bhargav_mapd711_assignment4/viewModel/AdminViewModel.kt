package com.example.khanjan_bhargav_mapd711_assignment4.viewModel

import com.example.khanjan_bhargav_mapd711_assignment4.db.AdminRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khanjan_bhargav_mapd711_assignment4.model.Admin
import com.example.khanjan_bhargav_mapd711_assignment4.model.Customer
import kotlinx.coroutines.launch


class AdminViewModel (
    private val userRepository: AdminRepository
): ViewModel() {
//    private val _users = MutableLiveData<List<User>>()
//    val users: LiveData<List<User>>
//        get() = _users
//
//    init {
//        getAllUsers()
//    }

    private val _users = MutableLiveData<List<Admin>>()
    val users: LiveData<List<Admin>>
        get() = _users

    // admin
    fun insertAdmin(user: Admin) {
        viewModelScope.launch {
            userRepository.insertAdmin(user)
        }
    }

    suspend fun isAdminUsernameExists(username: String): Boolean {
        return userRepository.isAdminUsernameExists(username)
    }

    suspend fun isAdminUsernameAndPasswordExists(username: String,password:String): Boolean {
        return userRepository.isAdminUsernameAndPasswordExists(username,password)
    }

    suspend fun getAdminUsernameAndPasswordExists(username: String,password:String): Admin? {
        return userRepository.getAdminByUsernameAndPassword(username,password)
    }

    suspend fun getUserByUsername(username: String): Admin? {
        return userRepository.getUserByUsername(username)
    }

    suspend fun getUserByUserId(customerId: Int): Admin? {
        return userRepository.getUserByUserId(customerId)
    }

    fun updateUser(user: Admin) {
        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }


}