package com.bimobelajar.mymovie.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class DataStoreManager(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

    private val USERNAME_KEY = stringPreferencesKey("username")
    private val EMAIL_KEY = stringPreferencesKey("email")
    private val PASSWORD_KEY = stringPreferencesKey("password")
    private val FULL_NAME_KEY = stringPreferencesKey("full_name")
    private val BIRTHDATE_KEY = stringPreferencesKey("birthdate")
    private val ADDRESS_KEY = stringPreferencesKey("address")

    suspend fun saveUsername(username: String) {
        context.dataStore.edit { prefs ->
            prefs[USERNAME_KEY] = username
        }
    }

    suspend fun saveEmail(email: String) {
        context.dataStore.edit { prefs ->
            prefs[EMAIL_KEY] = email
        }
    }

    suspend fun savePassword(password: String) {
        context.dataStore.edit { prefs ->
            prefs[PASSWORD_KEY] = password
        }
    }

    suspend fun getUsername(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[USERNAME_KEY]
    }

    suspend fun getEmail(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[EMAIL_KEY]
    }

    suspend fun getPassword(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[PASSWORD_KEY]
    }

    suspend fun getFullName(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[FULL_NAME_KEY]
    }

    suspend fun getBirthdate(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[BIRTHDATE_KEY]
    }

    suspend fun getAddress(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[ADDRESS_KEY]
    }

    suspend fun saveFullName(fullName: String) {
        context.dataStore.edit { prefs ->
            prefs[FULL_NAME_KEY] = fullName
        }
    }

    suspend fun saveBirthdate(birthdate: String) {
        context.dataStore.edit { prefs ->
            prefs[BIRTHDATE_KEY] = birthdate
        }
    }

    suspend fun saveAddress(address: String) {
        context.dataStore.edit { prefs ->
            prefs[ADDRESS_KEY] = address
        }
    }
}
