package com.farm.farmus_application.presentation.viewmodel.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farm.farmus_application.data.user.remote.dto.signup.SignUpReq
import com.farm.farmus_application.data.user.remote.dto.signup_verification.SignUpVerificationReq
import com.farm.farmus_application.data.user.remote.dto.verification.VerificationReq
import com.farm.farmus_application.domain.BaseResult
import com.farm.farmus_application.domain.user.entity.VerificationEntity
import com.farm.farmus_application.domain.user.usecase.SignUpUseCase
import com.farm.farmus_application.domain.user.usecase.SignUpVerificationUseCase
import com.farm.farmus_application.domain.user.usecase.VerificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val verificationUseCase: VerificationUseCase,
    private val signUpVerificationUseCase: SignUpVerificationUseCase,
): ViewModel() {
    private var _isVerificationSuccess = MutableLiveData<VerificationEntity>()
    var isVerificationSuccess: LiveData<VerificationEntity> = _isVerificationSuccess
    private var _isUserSignUpSuccess = MutableLiveData<Boolean>()
    var isUserSignUpSuccess: LiveData<Boolean> = _isUserSignUpSuccess
    private var _isEmailVerificationSuccess = MutableLiveData<Boolean>()
    var isEmailVerificationSuccess: LiveData<Boolean> = _isEmailVerificationSuccess

    fun userSignUp(signUpReq: SignUpReq) {
        viewModelScope.launch {
            signUpUseCase.invoke(signUpReq)
                .onStart {  }
                .catch {  }
                .collect {
                    when (it) {
                        is BaseResult.Success -> {
                            _isUserSignUpSuccess.postValue(it.data.isSuccess)
                        }
                        is BaseResult.Error -> {
                            Log.d("SignUpViewModel", "userSignUp: ${it.error}")
                        }
                    }
                }
        }
    }

    fun emailVerification(email: String) {
        viewModelScope.launch {
            try {
                val response = userRepo.getUserEmailVerification(email)
                if (response.isSuccessful) {
                    Log.e("EmailVerification","${response.body()}")
                    val result = response.body()?.isSuccess ?: false
                    _isEmailVerificationSuccess.postValue(result)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun signUpVerification(signUpVerificationReq: SignUpVerificationReq) {
        viewModelScope.launch {
            try {
                val response = userRepo.postUserSignupVerification(signUpVerificationReq)
                if (response.isSuccessful) {
                    Log.e("SignUpVerificationBody", response.body().toString())
//                    response.body()?.let {
//                        signUpRes = it.result
//                    }
                    // 인증번호가 발송되었습니다 이런거 띄워주는 용도?
                } else {
                    Log.e("SignUpVerificationCode", response.body().toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun signUpVerificationCheck(verificationReq: VerificationReq) {
        viewModelScope.launch {
            try {
                val response = userRepo.postUserVerification(verificationReq)
                if (response.isSuccessful) {
                    _isVerificationSuccess.postValue(response.body())
                } else {
                    Log.e("VerificationCode", response.body().toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}