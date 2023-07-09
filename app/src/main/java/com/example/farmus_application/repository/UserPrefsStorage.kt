package com.example.farmus_application.repository

import android.content.Context
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.delay

object UserPrefsStorage {
    private lateinit var prefs: EncryptedSharedPreferences

    fun init(context: Context) {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        prefs = EncryptedSharedPreferences.create(
            context,
            "user_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        ) as EncryptedSharedPreferences
    }

    var accessToken: String?
        get() = prefs.getString("access_token", null)
        set(value) = prefs.edit().putString("access_token", value).apply()

    var refreshToken: String?
        get() = prefs.getString("refresh_token", null)
        set(value) = prefs.edit().putString("refresh_token", value).apply()

    var name: String?
        get() = prefs.getString("user_name", null)
        set(value) = prefs.edit().putString("user_name", value).apply()

    var nickName: String?
        get() = prefs.getString("user_nickName", null)
        set(value) = prefs.edit().putString("user_nickName", value).apply()

    var email: String?
        get() = prefs.getString("user_email", null)
        set(value) = prefs.edit().putString("user_email", value).apply()

    var role: String?
        get() = prefs.getString("user_role", null)
        set(value) = prefs.edit().putString("user_role", value).apply()

    suspend fun clearStorage() {
        prefs.edit().clear().apply()
    }

//    suspend fun setUserData(
//        accessToken: String?,
//        refreshToken: String?,
//        name: String?,
//        nickName: String?,
//        email: String?,
//        role: String?
//    ) {
//        this.accessToken = accessToken
//        this.refreshToken = refreshToken
//        this.name = name
//        this.nickName = nickName
//        this.email = email
//        this.role = role
//    }
}
