package com.farm.farmus_application.viewmodel.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farm.farmus_application.model.user.signup.SignUpReq
import com.farm.farmus_application.model.user.signup_verification.SignUpVerificationReq
import com.farm.farmus_application.model.user.verification.VerificationReq
import com.farm.farmus_application.model.user.verification.VerificationRes
import com.farm.farmus_application.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepo: UserRepository
): ViewModel() {
    private var _isVerificationSuccess = MutableLiveData<VerificationRes>()
    var isVerificationSuccess: LiveData<VerificationRes> = _isVerificationSuccess
    private var _isUserSignUpSuccess = MutableLiveData<Boolean>()
    var isUserSignUpSuccess: LiveData<Boolean> = _isUserSignUpSuccess
    private var _isEmailVerificationSuccess = MutableLiveData<Boolean>()
    var isEmailVerificationSuccess: LiveData<Boolean> = _isEmailVerificationSuccess

    fun userSignUp(signUpReq: SignUpReq) {
        viewModelScope.launch {
            try {
                val response = userRepo.postUserSignup(signUpReq)
                if (response.isSuccessful) {
                    val result = response.body()?.isSuccess ?: false
                    Log.e("FifthSignUpFragment","${response.body()}")
                    _isUserSignUpSuccess.postValue(result)
                } else {
                    Log.e("SignUpResponseCode:", response.body().toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
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