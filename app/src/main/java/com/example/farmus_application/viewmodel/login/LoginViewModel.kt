package com.example.farmus_application.viewmodel.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmus_application.model.user.login.LoginReq
import com.example.farmus_application.model.user.login.LoginRes
import com.example.farmus_application.model.user.login.LoginResult
import com.example.farmus_application.repository.UserPrefsStorage
import com.example.farmus_application.repository.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
LiveData?
- LiveData는 observable 패턴을 사용하기에 데이터의 변화를 구독한 곳으로 통지하고, 업데이트한다.
- 메모리 누수 없는 사용을 보장한다.
- Lifecycle에 따라 LiveData의 이벤트를 제어한다.
- 항상 최신 데이터를 유지한다.
- 기기 회전이 일어나도 최신 데이터를 처리할 수 있도록 도와준다.(AAC-ViewModel과 함께 활용 시)
- LiveData의 확장도 지원한다.

MutableLiveData : 값의 get/set 모두를 할 수 있다.
LiveData : 값의 get()만을 할 수 있다.

factory를 응용하여 훨씬 적은 수고로 구상 클래스별 팩토리를 관리하여 응집성을 높이면서도
실제 사용시의 코드에서 정말 깔끔하게 클래스명만 넘기는 것으로 복잡한 별도의 factory객체를 전달하지 않아도 된다.
 **/
class LoginViewModel() : ViewModel(){
    private val TAG = "LoginViewModel"

    private val userRepo = UserRepository()
    val loginResponse: MutableLiveData<LoginResult?> = MutableLiveData()
    val errorResponse: MutableLiveData<String> = MutableLiveData()

    fun userLogin(params: LoginReq) {
        viewModelScope.launch {
            try{
                val response = userRepo.postUserLogin(params)

                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.isSuccess) {
                            loginResponse.postValue(it.result)
                        } else {
                            errorResponse.postValue(it.message)
                        }
                    }
                } else {
                    Log.e(TAG, response.message())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setUserData(params: LoginResult?) {
        viewModelScope.launch(Dispatchers.IO) {
            UserPrefsStorage.let {
                it.accessToken = params?.accesstoken
                it.refreshToken = null
                it.profileImgUrl = params?.profile
                // 이 구조로 데이터 삽입시 정상 삽입이 안됨.
//                UserPrefsStorage.setUserData(
//                    accessToken = it.accesstoken,
//                    refreshToken = null,
//                    name = it.name,
//                    nickName = it.nickName,
//                    email = it.email,
//                    role = it.role
//                )
            }
        }
    }
}