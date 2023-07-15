package com.example.farmus_application.database.home

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.farmus_application.model.home.Entity.SearchedKeyword

@Dao
interface SearchedKeywordDao {
    // 검색한 것 저장
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSearchedKeyword(searchedKeyword: SearchedKeyword)

    // keyword로 검색
    @Query("SELECT * FROM searchedKeyword WHERE keyword = :searchText")
    fun getSearchedKeyword(searchText: String): SearchedKeyword?

    // 한 개 삭제
    @Query("DELETE FROM searchedKeyword WHERE keyword = :searchText")
    fun deleteSearchedKeyword(searchText: String)

    // 전체 내용 삭제
    @Query("DELETE FROM searchedKeyword")
    fun deleteAllSearchedKeyword()

    // 검색한 것 일괄 불러오기
    @Query("SELECT * FROM searchedKeyword")
    fun getAllSearchedKeyword() : LiveData<List<SearchedKeyword>>
}