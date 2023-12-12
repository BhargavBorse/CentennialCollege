package com.example.khanjan_bhargav_mapd711_assignment4.model


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "admin")
data class Admin(
    @PrimaryKey(autoGenerate = true)
    val employeeId: Int = 0,
    val userName: String,
    val password: String,
    val firstname: String,
    val lastname: String,

) {
    @get:Ignore
    val employeeId1: String
        get() = "admin$employeeId"
}