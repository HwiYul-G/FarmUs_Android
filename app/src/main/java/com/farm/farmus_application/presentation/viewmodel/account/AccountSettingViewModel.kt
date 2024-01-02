package com.farm.farmus_application.presentation.viewmodel.account

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farm.farmus_application.data.user.remote.dto.withdrawal.WithdrawalRes
import com.farm.farmus_application.UserPrefsStorage
import com.farm.farmus_application.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountSettingViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val TAG = "AccountSettingViewModel"
    private var _isSuccess = MutableLiveData<WithdrawalRes>()
    var isSuccess : LiveData<WithdrawalRes> = _isSuccess

    fun clearUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            UserPrefsStorage.clearStorage()
        }
    }

    fun withdrawalUser() {
        viewModelScope.launch {
            val response = userRepository.deleteUserWithdrawal()
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    _isSuccess.postValue(result)
                }
            } else {
                Log.e("withdrawalUser","${response.errorBody()}")
            }
        }
    }

}