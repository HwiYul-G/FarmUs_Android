package com.example.farmus_application.viewmodel.account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmus_application.model.mypage.*
import com.example.farmus_application.repository.myPage.MyPageRepository
import kotlinx.coroutines.launch

class ProfileSettingViewModel : ViewModel(){
    private val myPageRepository = MyPageRepository()

    var editInfoNicknameResponse = MutableLiveData<Boolean>()
    var editInfoNameResponse = MutableLiveData<Boolean>()
    var editInfoPasswordResponse = MutableLiveData<Boolean>()
    var editInfoPhoneNumberResponse = MutableLiveData<Boolean>()
    var editInfoProfileImageResponse = MutableLiveData<Boolean>()

    // TODO : 아이디 변경 관련 API 존재하지 않음.

    fun patchEditInfoNickname(email: String, params : EditInfoNicknameReq){
        viewModelScope.launch {
            try{
                val response = myPageRepository.patchEditInfoNickname(email, params)
                if(response.isSuccessful){
                    response.body()?.let{
                        editInfoNicknameResponse.postValue(it.result)
                        if(!it.result){
                            Log.d("닉네임 변경 false : ", response.body().toString())
                        }
                    }
                }else{
                    Log.e("닉네임 변경 응답 실패 : ", response.body().toString())
                }
            }catch(e: java.lang.Exception){
                e.printStackTrace()
            }
        }
    }

    fun patchEditInfoName(email: String, params : EditInfoNameReq){
        viewModelScope.launch {
            try{
                val response = myPageRepository.patchEditInfoName(email, params)
                if(response.isSuccessful){
                    response.body()?.let{
                        editInfoNameResponse.postValue(it.result)
                    }
                }else{
                    Log.e("이름 변경 false : ", response.body().toString())
                }
            }catch(e: java.lang.Exception){
                e.printStackTrace()
            }
        }
    }

    fun patchEditInfoPassword(email: String, params : EditInfoPasswordReq){
        viewModelScope.launch {
            try{
                val response = myPageRepository.patchEditInfoPassword(email, params)
                if(response.isSuccessful){
                    response.body()?.let{
                        editInfoPasswordResponse.postValue(it.isSuccess)
                        Log.d("비밀번호 변경 success: ", response.body().toString())
                    }
                }else{
                    Log.e("비밀번호 변경 failed: ", response.body().toString())
                }
            }catch(e: java.lang.Exception){
                e.printStackTrace()
            }
        }
    }

    fun patchEditInfoPhoneNumber(email: String, params : EditInfoPhoneNumberReq) {
        viewModelScope.launch {
            try{
                val response = myPageRepository.patchEditInfoPhoneNumber(email, params)
                if(response.isSuccessful){
                    response.body()?.let{
                        editInfoPhoneNumberResponse.postValue(it.result)
                    }
                }else{
                    Log.e("전화번호 변경 false: ", response.body().toString())
                }
            }catch(e: java.lang.Exception){
                e.printStackTrace()
            }
        }
    }

    fun patchEditInfoProfileImg(email: String, params : EditInfoProfileImgReq) {
        viewModelScope.launch {
            try{
                val response = myPageRepository.patchEditInfoProfileImg(email, params)
                if(response.isSuccessful){
                    response.body()?.let{
                        editInfoProfileImageResponse.postValue(it.result)
                    }
                }else{
                    Log.e("이미지 변경 false: ", response.body().toString())
                }
            }catch(e: java.lang.Exception){
                e.printStackTrace()
            }
        }
    }

}