package com.example.farmus_application.viewmodel.farm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmus_application.model.reserve.reserve_list.ReserveListRes
import com.example.farmus_application.repository.reserve.ReserveRepository
import kotlinx.coroutines.launch

class FarmListViewModel: ViewModel() {

    private val reserveRepo = ReserveRepository()
    private var _currentFarmList = MutableLiveData<ReserveListRes>()
    var currentFarmList: LiveData<ReserveListRes> = _currentFarmList

    private var _pastFarmList = MutableLiveData<ReserveListRes>()
    var pastFarmList: LiveData<ReserveListRes> = _pastFarmList

    fun getCurrentList(email: String) {
        viewModelScope.launch {
            try {
                val response = reserveRepo.getCurrentList(email)
                if (response.isSuccessful) {
                    _currentFarmList.postValue(response.body())
                } else {
                    Log.e("CurrentFarmListError:","${response.body()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getPastList(email: String) {
        viewModelScope.launch {
            try {
                val response = reserveRepo.getPastList(email)
                if (response.isSuccessful) {
                    _pastFarmList.postValue(response.body())
                } else {
                    Log.e("PastFarmListError:","${response.body()}")
                }
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}