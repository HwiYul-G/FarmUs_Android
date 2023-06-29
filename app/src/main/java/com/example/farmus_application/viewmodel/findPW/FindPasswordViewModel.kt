package com.example.farmus_application.viewmodel.findPW

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmus_application.model.user.findpassword.FindPasswordRes
import com.example.farmus_application.repository.user.UserRepository
import kotlinx.coroutines.launch

class FindPasswordViewModel : ViewModel(){
    private val userRepository = UserRepository()
    val findPasswordResponse : MutableLiveData<FindPasswordRes> = MutableLiveData()

    fun findPassword(userEmail: String){
        viewModelScope.launch {
            try {
                val response = userRepository.getUserFindPassword(userEmail = userEmail)
                if(response.isSuccessful){
                    Log.d("FindPassword Communication Success: ", response.body().toString())
                    val responseBody = response.body()
                    responseBody?.let {
                        if(it.result){
                            // 비밀번호를 찾은 경우
                            findPasswordResponse.postValue(it)
                        }else{
                            // 비밀번호를 찾지 못한 경우
                            Log.d("FindPassword Failed : ", response.body().toString())
                            findPasswordResponse.postValue(it)
                        }
                    }
                }else{
                    Log.d("FindPassword Communication Success: ", response.body().toString())
                    response.message()
                }
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }
}