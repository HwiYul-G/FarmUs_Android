package com.example.farmus_application.viewmodel.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmus_application.repository.UserPrefsStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountSettingViewModel() : ViewModel() {
    private val TAG = "AccountSettingViewModel"

    fun clearUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            UserPrefsStorage.clearStorage()
        }
    }
}