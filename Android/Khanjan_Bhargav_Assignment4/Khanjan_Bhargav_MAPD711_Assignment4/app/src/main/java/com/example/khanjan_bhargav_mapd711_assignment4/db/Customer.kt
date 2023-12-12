package com.example.khanjan_bhargav_mapd711_assignment4.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.khanjan_bhargav_mapd711_assignment4.model.Admin
import com.example.khanjan_bhargav_mapd711_assignment4.model.Customer
import com.example.khanjan_bhargav_mapd711_assignment4.model.Orders
import com.example.khanjan_bhargav_mapd711_assignment4.model.Pizza

@Database(entities = [Customer::class,Admin::class,Pizza::class,Orders::class], version = 1)
abstract class PizzaDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun AdminDao(): AdminDao
    abstract fun PizzaDao(): PizzaDao
    abstract fun OrdersDao(): OrdersDao

    companion object {
        @Volatile
        private var INSTANCE: PizzaDatabase? = null

        fun getDatabaseInstance(context: Context): PizzaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PizzaDatabase::class.java,
                    "pizza_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
