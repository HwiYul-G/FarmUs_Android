package com.example.farmus_application.viewmodel.calendar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmus_application.model.reserve.request.ReserveRequestReq
import com.example.farmus_application.model.reserve.request.ReserveRequestRes
import com.example.farmus_application.repository.reserve.ReserveRepository
import kotlinx.coroutines.launch

class CalendarBottomSheetViewModel: ViewModel() {

    private val reserveRepo = ReserveRepository()

    private var _isSuccessReserve = MutableLiveData<ReserveRequestRes>()
    var isSuccessReserve: LiveData<ReserveRequestRes> = _isSuccessReserve


    fun postReserveRequest(reserveRequestReq: ReserveRequestReq) {
        viewModelScope.launch {
            try {
                val response = reserveRepo.postReserveRequest(reserveRequestReq)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _isSuccessReserve.postValue(it)
                    }
                } else {
                    Log.e("postReserveRequestResponseCode",response.body().toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}