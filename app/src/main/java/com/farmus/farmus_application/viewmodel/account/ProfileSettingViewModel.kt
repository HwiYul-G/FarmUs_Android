package com.farmus.farmus_application.viewmodel.account

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farmus.farmus_application.model.mypage.*
import com.farmus.farmus_application.repository.UserPrefsStorage
import com.farmus.farmus_application.repository.myPage.MyPageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

class ProfileSettingViewModel : ViewModel() {

    private val myPageRepository = MyPageRepository()

    // LiveData
    private var _editInfoNicknameResponse = MutableLiveData<Boolean>()
    val editInfoNicknameResponse : LiveData<Boolean> get() = _editInfoNicknameResponse

    private var _editInfoNameResponse = MutableLiveData<Boolean>()
    val editInfoNameResponse : LiveData<Boolean> get() = _editInfoNameResponse

    private var _editInfoPasswordResponse = MutableLiveData<Boolean>()
    val editInfoPasswordResponse : LiveData<Boolean> get() = _editInfoPasswordResponse

    private var _editInfoPhoneNumberResponse = MutableLiveData<Boolean>()
    val editInfoPhoneNumberResponse : LiveData<Boolean> get() = _editInfoPhoneNumberResponse

    private var _editInfoProfileImageResponse = MutableLiveData<MyPageProfileImageRes>()
    val editInfoProfileImageResponse : LiveData<MyPageProfileImageRes> get() = _editInfoProfileImageResponse

    // TODO : 아이디 변경 관련 API 존재하지 않음.

    fun patchEditInfoNickname(email: String, params: EditInfoNicknameReq) {
        viewModelScope.launch {
            try {
                val response = myPageRepository.patchEditInfoNickname(email, params)
                if (response.isSuccessful) {
                    response.body()?.let {
                        // UserPrefsStorage의 accessToken 업데이트
                        UserPrefsStorage.accessToken = it.accesstoken
                        _editInfoNicknameResponse.postValue(it.result)
                        if (!it.result) {
                            Log.d("닉네임 변경 false : ", response.body().toString())
                        }
                    }
                } else {
                    Log.e("닉네임 변경 응답 실패 : ", response.body().toString())
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    fun patchEditInfoName(email: String, params: EditInfoNameReq) {
        viewModelScope.launch {
            try {
                val response = myPageRepository.patchEditInfoName(email, params)
                if (response.isSuccessful) {
                    response.body()?.let {
                        // UserPrefsStorage의 accessToken 업데이트
                        UserPrefsStorage.accessToken = it.accesstoken
                        _editInfoNameResponse.postValue(it.result)
                    }
                } else {
                    Log.e("이름 변경 false : ", response.body().toString())
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    fun patchEditInfoPassword(email: String, params: EditInfoPasswordReq) {
        viewModelScope.launch {
            try {
                val response = myPageRepository.patchEditInfoPassword(email, params)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _editInfoPasswordResponse.postValue(it.isSuccess)
                        Log.d("비밀번호 변경 success: ", response.body().toString())
                    }
                } else {
                    Log.e("비밀번호 변경 failed: ", response.body().toString())
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    fun patchEditInfoPhoneNumber(email: String, params: EditInfoPhoneNumberReq) {
        viewModelScope.launch {
            try {
                val response = myPageRepository.patchEditInfoPhoneNumber(email, params)
                if (response.isSuccessful) {
                    response.body()?.let {
                        // UserPrefsStorage의 accessToken 업데이트
                        UserPrefsStorage.accessToken = it.accesstoken
                        _editInfoPhoneNumberResponse.postValue(it.result)
                    }
                } else {
                    Log.e("전화번호 변경 false: ", response.body().toString())
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    fun patchEditInfoProfileImg(email: String, bitmap: Bitmap) {
        viewModelScope.launch {
            try {
                val multipartFile = bitmapToMultiPart(bitmap)
                val response = myPageRepository.patchEditInfoProfileImg(email, multipartFile)
                if (response.isSuccessful) {
                    response.body()?.let {
                        // UserPrefsStorage의 accessToken 업데이트
                        UserPrefsStorage.accessToken = it.accesstoken
                        UserPrefsStorage.profileImgUrl = it.photoUrl
                        // result : boolean과 imgaUrl : string만 넣고 싶음
                        _editInfoProfileImageResponse.postValue(it)
                    }
                } else {
                    Log.e("이미지 변경 false: ", response.body().toString())
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    fun clearUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            UserPrefsStorage.clearStorage()
        }
    }

    private fun bitmapToMultiPart(bitmap: Bitmap): MultipartBody.Part {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val imageBytes = byteArrayOutputStream.toByteArray()
        val requestBody: RequestBody = imageBytes.toRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(
            "file",
            "profileImg.png",
            requestBody
        )
    }


}