package com.example.farmus_application.viewmodel.account

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farm.farmus_application.UserPrefsStorage
import com.farm.farmus_application.repository.farm.FarmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnrollFarmerViewModel @Inject constructor(
    private val farmRepository: FarmRepository
): ViewModel() {
    private var _farmRegister = MutableLiveData<Boolean>()
    var farmRegister: LiveData<Boolean> = _farmRegister

    fun patchFarmRegister() {
        viewModelScope.launch {
            try {
                val response = farmRepository.patchFarmRegister()
                if (response.isSuccessful) {
                    response.body()?.let {
                        UserPrefsStorage.accessToken = it.accessToken
                        _farmRegister.postValue(it.result)
                    }
                } else {
                    Log.e("농장주 등록 실패:","${response.body()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}