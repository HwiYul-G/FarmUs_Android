package com.farm.farmus_application.presentation.viewmodel.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farm.farmus_application.domain.BaseResult
import com.farm.farmus_application.domain.user.entity.FindPaswordEntity
import com.farm.farmus_application.domain.user.usecase.FindPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindPasswordViewModel @Inject constructor(
    private val findPasswordUseCase: FindPasswordUseCase,
) : ViewModel() {
    val findPasswordResponse: MutableLiveData<FindPaswordEntity> = MutableLiveData()

    fun findPassword(userEmail: String) {
        viewModelScope.launch {
            findPasswordUseCase.invoke(userEmail)
                .onStart { }
                .catch { }
                .collect {
                    when (it) {
                        is BaseResult.Success -> {
                            findPasswordResponse.postValue(it.data)
                        }

                        is BaseResult.Error -> {
                            Log.d("FindPasswordViewModel", "findPassword: ${it.error}")
                        }
                    }
                }
        }
    }

}