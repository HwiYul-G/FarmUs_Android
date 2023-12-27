package com.farm.farmus_application.data.common

import com.google.gson.annotations.SerializedName

// List<T>와 T에 대한 wrapper 클래스가 필요하지만, 현재 server에서 날라오는 정보가 불규칙하여 애매한 상황..
// 몇 가지는 wrapper 를 사용할만한 규칙을 따르고 있어서 작성은 했지만, 전체적인 주고 받는 데이터 로직을 잘 관리할 때 유용할 것.
data class WrappedResponse<T>(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: T? = null
)