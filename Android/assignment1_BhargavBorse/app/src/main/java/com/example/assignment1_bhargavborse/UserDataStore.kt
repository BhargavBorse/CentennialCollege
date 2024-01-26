package com.example.assignment1_bhargavborse

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class UserDataStore(context: Context) {

    private val dataStore: DataStore<Preferences> = context.dataStore

    val userData: Flow<UserData> = dataStore.data.map { preferences ->
        UserData(
            username = preferences[USER_NAME_KEY] ?: "",
            email = preferences[USER_EMAIL_KEY] ?: "",
            id = preferences[USER_ID_KEY] ?: ""
        )
    }

    suspend fun saveUserData(username: String, email: String, id: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = username
            preferences[USER_EMAIL_KEY] = email
            preferences[USER_ID_KEY] = id
        }
    }

    suspend fun clearUserData() {
        dataStore.edit { preferences ->
            preferences.remove(USER_NAME_KEY)
            preferences.remove(USER_EMAIL_KEY)
            preferences.remove(USER_ID_KEY)
        }
    }

    companion object {
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
    }
}

data class UserData(
    val username: String,
    val email: String,
    val id: String
)
