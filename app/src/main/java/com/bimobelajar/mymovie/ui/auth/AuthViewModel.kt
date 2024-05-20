package com.bimobelajar.mymovie.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bimobelajar.mymovie.data.DataStoreManager
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val dataStore = DataStoreManager(application)

    fun login(email: String, password: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val storedEmail = dataStore.getEmail()
            val storedPassword = dataStore.getPassword()
            callback(email == storedEmail && password == storedPassword)
        }
    }

    fun register(username: String, email: String, password: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            dataStore.saveUsername(username)
            dataStore.saveEmail(email)
            dataStore.savePassword(password)
            callback(true)
        }
    }
}
