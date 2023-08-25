package com.farm.farmus_application.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object UserPrefsStorage {
    private lateinit var prefs: EncryptedSharedPreferences

    private const val SEARCHED_HISTORY_PREFS_NAME = "searched_history_prefs"
    private lateinit var searchedHistoryPrefs: SharedPreferences

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

        searchedHistoryPrefs =
            context.getSharedPreferences(SEARCHED_HISTORY_PREFS_NAME, Context.MODE_PRIVATE)
    }

    var accessToken: String?
        get() = prefs.getString("access_token", null)
        set(value) = prefs.edit().putString("access_token", value).apply()

    var refreshToken: String?
        get() = prefs.getString("refresh_token", null)
        set(value) = prefs.edit().putString("refresh_token", value).apply()

    var profileImgUrl : String?
        get() = prefs.getString("profile_image_url", null)
        set(value) = prefs.edit().putString("profile_image_url", value).apply()

    // token 내부에 있는 정보를 추출해 사용하기로 해서 주석 처리.
//    var name: String?
//        get() = prefs.getString("user_name", null)
//        set(value) = prefs.edit().putString("user_name", value).apply()
//
//    var nickName: String?
//        get() = prefs.getString("user_nickName", null)
//        set(value) = prefs.edit().putString("user_nickName", value).apply()
//
//    var email: String?
//        get() = prefs.getString("user_email", null)
//        set(value) = prefs.edit().putString("user_email", value).apply()
//
//    var role: String?
//        get() = prefs.getString("user_role", null)
//        set(value) = prefs.edit().putString("user_role", value).apply()

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

    // HomeSearchFragment에서 사용할 최근 검색 기록 저장 chip 관련
    var recentSearches: Set<String>
        get() = searchedHistoryPrefs.getStringSet("recent_searches", emptySet()) ?: emptySet()
        set(value) = searchedHistoryPrefs.edit().putStringSet("recent_searches", value).apply()

    fun addRecentSearch(searchText : String){
        val searches = recentSearches.toMutableSet()
        searches.add(searchText)
        recentSearches = searches
    }

    fun removeRecentSearch(searchText : String){
        val searches = recentSearches.toMutableSet()
        searches.remove(searchText)
        recentSearches = searches
    }

    fun clearRecentSearches(){
        recentSearches = emptySet()
    }


}
