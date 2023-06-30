package com.example.farmus_application.viewmodel.findAccount

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmus_application.model.user.findaccount.FindAccountRes
import com.example.farmus_application.model.user.signup_verification.SignUpVerificationReq
import com.example.farmus_application.model.user.signup_verification.SignUpVerificationRes
import com.example.farmus_application.model.user.verification.VerificationReq
import com.example.farmus_application.model.user.verification.VerificationRes
import com.example.farmus_application.repository.user.UserRepository
import kotlinx.coroutines.launch

class FindAccountViewModel : ViewModel() {
    private val userRepository = UserRepository()

    val authCodeResponse : MutableLiveData<SignUpVerificationRes> = MutableLiveData()
    val verificationResponse : MutableLiveData<VerificationRes> = MutableLiveData()
    val findAccountResponse : MutableLiveData<FindAccountRes> = MutableLiveData()


    fun postUserSignupVerification(params : SignUpVerificationReq){
        viewModelScope.launch {
            try{
                val response = userRepository.postUserSignupVerification(params)
                if(response.isSuccessful){
                    Log.d("postUserSignupVerification Succeess : ", response.body().toString())
                    val responseBody = response.body()
                    responseBody?.let {
                        if(it.result){
                            // 인증번호 발송 성공
                            authCodeResponse.postValue(it)
                            Log.d("send code : ", response.body().toString())
                        }else{
                            // 인증번호 발송 실패
                            authCodeResponse.postValue(it)
                            Log.d("can't send code : ", response.body().toString())
                        }
                    }
                }else{
                    response.message()
                }
            } catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    fun postUserVerification(params : VerificationReq){
        viewModelScope.launch {
            try{
                val response = userRepository.postUserVerification(params)
                if(response.isSuccessful){
                    Log.d("postUserVerification Succeess : ", response.body().toString())
                    val responseBody = response.body()
                    responseBody?.let {
                        if(it.result){
                            // 아이디 찾기 성공
                            verificationResponse.postValue(it)
                            Log.d("email received : ", response.body().toString())
                        }else{
                            // 인증번호 발송 실패
                            verificationResponse.postValue(it)
                            Log.d("can't be received email : ", response.body().toString())
                        }
                    }
                }else{
                    response.message()
                }
            } catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    fun findAccount(name : String, phone : String){
        viewModelScope.launch{
            try{
                val response = userRepository.getUserFindAccount(name = name, phoneNumber = phone)
                if(response.isSuccessful){
                    Log.d("FindAccount Communication Success: ", response.body().toString())
                    val responseBody = response.body()
                    responseBody?.let {
                        if(it.result){
                            // 아이디가 존재해서 찾는 경우
                            findAccountResponse.postValue(it)
                        }else{
                            // 아이디를 찾지 못한 경우
                            Log.d("FindAccount Failed : ", response.body().toString())
                            findAccountResponse.postValue(it)
                        }
                    }
                }else{
                    response.message()
                }

                Log.e("FindAccountViewModel : ", response.body().toString())
            } catch (e : Exception){
                e.printStackTrace()
            }
        }
    }
}