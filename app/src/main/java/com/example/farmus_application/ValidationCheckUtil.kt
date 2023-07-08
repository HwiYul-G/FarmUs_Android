package com.example.farmus_application

import android.util.Patterns
import java.util.regex.Pattern

object ValidationCheckUtil {
    // 이메일 검사
    fun isEmailValid(email: String): Boolean {
        val emailPattern : Pattern = Patterns.EMAIL_ADDRESS
        return emailPattern.matcher(email).matches()
    }

    // 비밀번호 검사
    fun checkPassword(password: String): Boolean {
        val pattern = Regex("^[a-zA-Z0-9]+$")
        val lengthCheck = password.length in 6..20
        return pattern.matches(password) && lengthCheck
    }

    // 휴대폰 검사
    fun isPhoneNumberValid(phoneNumber : String) : Boolean{
        val regex = Regex("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$")
        if(regex.matches(phoneNumber)) return true
        return false
    }

    // 닉네임 검사
    enum class NicknameStatus(val value: Int) {
        VALID(0),
        SPECIAL_CHARACTERS(1),
        SPACE(2),
        BAD_WORDS(3)
    }
    fun checkNickname(nickname: String): NicknameStatus {
        val pattern = Regex("^[a-zA-Z0-9가-힣]+$")
        val specialCharCheck = !pattern.matches(nickname)
        val spaceCheck = nickname.contains(" ")
        val badWords = listOf("바보", "멍청이", "병신", "Fuck", "Fucking") // 욕설, 비속어, 금지단어 목록

        return when {
            specialCharCheck -> NicknameStatus.SPECIAL_CHARACTERS
            spaceCheck -> NicknameStatus.SPACE
            badWords.any { nickname.contains(it) } -> NicknameStatus.BAD_WORDS
            else -> NicknameStatus.VALID
        }
    }

    // 이름 검사

}