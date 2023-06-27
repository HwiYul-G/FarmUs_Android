package com.example.farmus_application.viewmodel.findAccount

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmus_application.model.user.findaccount.FindAccountRes
import com.example.farmus_application.repository.user.UserRepository
import kotlinx.coroutines.launch

class FindAccountViewModel : ViewModel() {
    private val userRepository = UserRepository()
    val findAccountResponse : MutableLiveData<FindAccountRes> = MutableLiveData()

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