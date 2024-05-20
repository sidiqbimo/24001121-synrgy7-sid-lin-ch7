package com.bimobelajar.mymovie.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bimobelajar.mymovie.MyApplication
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStoreManager = MyApplication.dataStoreManager

    suspend fun login(email: String, password: String): Boolean {
        val storedEmail = dataStoreManager.email.first()
        val storedPassword = dataStoreManager.password.first()
        return email == storedEmail && password == storedPassword
    }

    fun register(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val storedEmail = dataStoreManager.email.first()
            if (storedEmail != null) {
                onError("User already registered")
            } else {
                dataStoreManager.saveEmail(email)
                dataStoreManager.savePassword(password)
                onSuccess()
            }
        }
    }
}
