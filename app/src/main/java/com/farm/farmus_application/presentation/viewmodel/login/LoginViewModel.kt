package com.farm.farmus_application.presentation.viewmodel.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farm.farmus_application.data.user.remote.dto.login.LoginRequest
import com.farm.farmus_application.UserPrefsStorage
import com.farm.farmus_application.domain.BaseResult
import com.farm.farmus_application.domain.user.entity.LoginEntity
import com.farm.farmus_application.domain.user.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

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
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLoginUseCase: LoginUseCase,
) : ViewModel(){
    private val TAG = "LoginViewModel"

    val loginResponse: MutableLiveData<LoginEntity> = MutableLiveData()
    val errorResponse: MutableLiveData<String> = MutableLiveData()

    fun userLogin(params: LoginRequest) {
        viewModelScope.launch {
            userLoginUseCase.invoke(params)
                .onStart {
                    // 로딩 관련 코드
                }
                .catch {
                    // 오류 발생 처리 코드
                }
                .collect {
                    when (it) {
                        is BaseResult.Success -> {
                            loginResponse.postValue(it.data)
                            // TODO : UserPref 관련
                            setUserData(it.data)
                        }
                        is BaseResult.Error -> {
                            Log.d(TAG, "userLogin: ${it.error}")
                        }
                    }
                }
        }
    }

    // TODO : UserPrefStorage에 대한 코드를 어떻게 할지 고민해야함...
    fun setUserData(params: LoginEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            UserPrefsStorage.let {
                it.accessToken = params.accessToken
                it.refreshToken = null
                it.profileImgUrl = params.profile
            }
        }
    }

}