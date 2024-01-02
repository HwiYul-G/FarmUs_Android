package com.farm.farmus_application.presentation.viewmodel.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farm.farmus_application.domain.BaseResult
import com.farm.farmus_application.domain.user.entity.FindAccountEntity
import com.farm.farmus_application.domain.user.usecase.FindAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindAccountViewModel @Inject constructor(
    private val findAccountUseCase: FindAccountUseCase
) : ViewModel() {
    // ** 각 ViewModel과 Activity 혹은 Fragment 부분에 state를 통해 처리하는 것이 좋음 **
    // ** state는 ViewModel에서 관리하고, Activity 혹은 Fragment에서는 관찰만 하도록 처리 **
    // ** state는 sealed class로 처리하는 것이 좋음 **
    // 하지만 현 상황에선 기존의 것을 이용하는 방식으로 처리하겠습니다.ㅜㅜ

    // 인증번호 발송 응답 라이브 데이터
    val findAccountRes: MutableLiveData<FindAccountEntity> = MutableLiveData()

    fun findAccount(name: String, phone: String) {
        viewModelScope.launch {
            findAccountUseCase.invoke(name, phone)
                .onStart {
                    // 로딩 처리를 하는 코드
                }
                .catch { e ->
                    // 오류가 발생한 토스트 메시지를 처리하는 코드 추천
                    Log.d("FindAccountViewModel", "findAccount: ${e.message}")
                }
                .collect {
                    when (it) {
                        is BaseResult.Success -> {
                            findAccountRes.postValue(it.data)
                        }
                        is BaseResult.Error -> {
                            Log.d("FindAccountViewModel", "findAccount: ${it.error}")
                        }
                    }
                }
        }
    }
}