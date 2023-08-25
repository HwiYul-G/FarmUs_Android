package com.farm.farmus_application.viewmodel.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farm.farmus_application.model.user.findaccount.FindAccountRes
import com.farm.farmus_application.repository.user.UserRepository
import kotlinx.coroutines.launch

class FindAccountViewModel : ViewModel() {
    private val userRepository = UserRepository()

    // 인증번호 발송 응답 라이브 데이터
    val findAccountRes : MutableLiveData<FindAccountRes> = MutableLiveData()

    fun findAccount(name : String, phone : String){
        viewModelScope.launch{
            try{
                val response = userRepository.getUserFindAccount(name = name, phoneNumber = phone)
                if(response.isSuccessful){
                    val responseBody = response.body()
                    responseBody?.let {
                        Log.d("FindAccountViewModel", "findAccount: ${responseBody.toString()}")
                        findAccountRes.postValue(it)
                    }
                }else{
                    response.message()
                }
            } catch (e : Exception){
                e.printStackTrace()
            }
        }
    }
}