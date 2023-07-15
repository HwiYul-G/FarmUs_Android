package com.example.farmus_application.repository.home

import androidx.lifecycle.LiveData
import com.example.farmus_application.database.home.SearchedKeywordDao
import com.example.farmus_application.model.home.Entity.SearchedKeyword

class HomeRepository(private val searchedKeywordDao: SearchedKeywordDao) : HomeDataSourceInterface {

    val allSearchedKeyword : LiveData<List<SearchedKeyword>> = searchedKeywordDao.getAllSearchedKeyword()

    override suspend fun insertSearchedKeyword(searchedKeyword: SearchedKeyword) {
        searchedKeywordDao.saveSearchedKeyword(searchedKeyword)
    }

    override suspend fun getSearchedKeyword(searchText: String): SearchedKeyword? {
        return searchedKeywordDao.getSearchedKeyword(searchText)
    }

    override suspend fun deleteSearchedKeyword(searchText: String) {
        searchedKeywordDao.deleteSearchedKeyword(searchText)
    }

    override suspend fun deleteAllSearchedKeyword() {
        searchedKeywordDao.deleteAllSearchedKeyword()
    }

}