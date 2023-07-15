package com.example.farmus_application.model.home.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchedKeyword")
data class SearchedKeyword(
    @PrimaryKey val keyword: String,
    @ColumnInfo(name = "date") val date: String?,
)
