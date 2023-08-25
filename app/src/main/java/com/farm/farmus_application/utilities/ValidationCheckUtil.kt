package com.farm.farmus_application.utilities

import android.util.Patterns
import java.util.regex.Pattern

object ValidationCheckUtil {
    // 이메일 검사 : 이메일 형식 체크
    fun isEmailValid(email: String): Boolean {
        val emailPattern : Pattern = Patterns.EMAIL_ADDRESS
        return emailPattern.matcher(email).matches()
    }

    // 비밀번호 검사 : 6자 이상 20자 이하, 영문, 숫자마 사용
    fun checkPassword(password: String): Boolean {
        val pattern = Regex("^[a-zA-Z0-9]+$")
        val lengthCheck = password.length in 6..20
        return pattern.matches(password) && lengthCheck
    }

    // 휴대폰 검사 : 01x - 3or4자리 - 4자리
    fun isPhoneNumberValid(phoneNumber : String) : Boolean{
        val regex = Regex("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$")
        if(regex.matches(phoneNumber)) return true
        return false
    }

    // TODO : 닉네임 유효성 검사는 추가적인 작업이 필요할 수 있음. 임시로 처리
    // 닉네임 검사 : 10자 미만, 특수문자, 공백, 욕설, 비속어, 금지단어 사용 불가
    enum class NicknameStatus(val value: Int) {
        VALID(0),
        SPECIAL_CHARACTERS(1),
        SPACE(2),
        BAD_WORDS(3),
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

    // TODO : 이름 유효성 검사는 추가적인 작업이 필요할 수 있음. 임시로 처리.
    enum class NameStatus {
        VALID, SPECIAL_CHAR, BAD_WORDS, LIMITED_LENGTH
    }
    fun checkName(name: String): NameStatus {
        val pattern = Regex("^[a-zA-Z0-9가-힣]+$")
        val specialCharCheck = !pattern.matches(name)
        val lengthInRange = name.length in 3..20
        val badWords = listOf("바보", "멍청이", "병신", "Fuck", "Fucking") // 욕설, 비속어, 금지단어 목록

        return when {
            specialCharCheck -> NameStatus.SPECIAL_CHAR
            !lengthInRange -> NameStatus.LIMITED_LENGTH
            badWords.any { name.contains(it, ignoreCase = true) } -> NameStatus.BAD_WORDS
            else -> NameStatus.VALID
        }
    }
}