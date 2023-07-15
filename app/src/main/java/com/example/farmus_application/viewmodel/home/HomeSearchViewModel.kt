package com.example.farmus_application.viewmodel.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.farmus_application.database.home.SearchedKeywordDatabase
import com.example.farmus_application.model.home.Entity.SearchedKeyword
import com.example.farmus_application.repository.home.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeSearchViewModel(application: Application) : AndroidViewModel(application) {

    val allSearchedKeyword : LiveData<List<SearchedKeyword>>
    private val homeRepository : HomeRepository

    init {
        val dao = SearchedKeywordDatabase.getInstance(application).getSearchedKeywordDao()
        homeRepository = HomeRepository(dao)
        allSearchedKeyword = homeRepository.allSearchedKeyword
    }

    fun insertSearchedKeyword(searchedKeyword: SearchedKeyword) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingKeyword = homeRepository.getSearchedKeyword(searchedKeyword.keyword)
            if(existingKeyword == null) {
                homeRepository.insertSearchedKeyword(searchedKeyword)
            }
        }
    }

    fun deleteSearchedKeyword(searchText: String) {
        viewModelScope.launch(Dispatchers.IO){
            homeRepository.deleteSearchedKeyword(searchText)
        }
    }

    fun deleteAllSearchedKeyword() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.deleteAllSearchedKeyword()
        }
    }


    fun getDate() : String {
        val date = System.currentTimeMillis()
        val dateFormat = android.text.format.DateFormat.format("yyyy-MM-dd", date)
        return dateFormat.toString()
    }

}