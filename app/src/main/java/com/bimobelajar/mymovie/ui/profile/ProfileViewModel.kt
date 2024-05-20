package com.bimobelajar.mymovie.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bimobelajar.mymovie.data.DataStoreManager
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val dataStore = DataStoreManager(application)

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String> = _fullName

    private val _birthdate = MutableLiveData<String>()
    val birthdate: LiveData<String> = _birthdate

    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    init {
        viewModelScope.launch {
            _username.postValue(dataStore.getUsername())
            _fullName.postValue(dataStore.getFullName())
            _birthdate.postValue(dataStore.getBirthdate())
            _address.postValue(dataStore.getAddress())
        }
    }

    fun saveChanges(username: String, fullName: String, birthdate: String, address: String) {
        viewModelScope.launch {
            dataStore.saveUsername(username)
            dataStore.saveFullName(fullName)
            dataStore.saveBirthdate(birthdate)
            dataStore.saveAddress(address)
        }
    }
}
