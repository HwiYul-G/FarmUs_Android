package com.example.farmus_application.database.home

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.farmus_application.model.home.Entity.SearchedKeyword

@Database(entities = arrayOf(SearchedKeyword::class), version = 3, exportSchema = false)
abstract class SearchedKeywordDatabase : RoomDatabase() {

    abstract fun getSearchedKeywordDao(): SearchedKeywordDao

    companion object {
        @Volatile
        private var instance: SearchedKeywordDatabase? = null
        fun getInstance(context: Context): SearchedKeywordDatabase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SearchedKeywordDatabase::class.java,
                    "searched_keyword_database"
                ).fallbackToDestructiveMigration().build() // version 변경 시 기존 데이터 삭제
                Companion.instance = instance
                return instance
            }
        }
    }
}