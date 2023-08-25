package com.farm.farmus_application.model.user.signup_verification

import com.google.gson.annotations.SerializedName

data class SignUpVerificationReq(
    @SerializedName("phoneNumber") val phoneNumber: String
)