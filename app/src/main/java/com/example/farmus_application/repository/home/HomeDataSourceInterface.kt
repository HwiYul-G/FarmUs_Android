package com.example.farmus_application.repository.home

import com.example.farmus_application.model.home.Entity.SearchedKeyword

interface HomeDataSourceInterface {

    // 검색 기록 Chips 관련
    suspend fun insertSearchedKeyword(searchedKeyword: SearchedKeyword)
    suspend fun getSearchedKeyword(searchText: String): SearchedKeyword?
    suspend fun deleteSearchedKeyword(searchText: String)
    suspend fun deleteAllSearchedKeyword()

}