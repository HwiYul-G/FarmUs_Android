package com.farm.farmus_application.viewmodel.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farm.farmus_application.repository.UserPrefsStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountSettingViewModel @Inject constructor() : ViewModel() {
    private val TAG = "AccountSettingViewModel"

    fun clearUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            UserPrefsStorage.clearStorage()
        }
    }
}