package com.farm.farmus_application.presentation.viewmodel.calendar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farm.farmus_application.model.farm.phone.PhoneNumberRes
import com.farm.farmus_application.data.reserve.remote.dto.request.ReserveRequestReq
import com.farm.farmus_application.data.reserve.remote.dto.request.ReserveRequestRes
import com.farm.farmus_application.data.reserve.remote.dto.unbookable.ReserveUnBookableRes
import com.farm.farmus_application.repository.farm.FarmRepository
import com.farm.farmus_application.repository.reserve.ReserveRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val reserveRepo: ReserveRepository,
    private val farmRepo: FarmRepository
): ViewModel() {

    private var _isSuccessReserve = MutableLiveData<ReserveRequestRes>()
    var isSuccessReserve: LiveData<ReserveRequestRes> = _isSuccessReserve
    private var _unBookable = MutableLiveData<ReserveUnBookableRes>()
    var unBookable: LiveData<ReserveUnBookableRes> = _unBookable
    private var _farmerPhoneNumber = MutableLiveData<PhoneNumberRes>()
    var farmerPhoneNumber: LiveData<PhoneNumberRes> = _farmerPhoneNumber


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
    fun getReserveUnBookable(farmId: String) {
        viewModelScope.launch {
            try {
                val response = reserveRepo.getReserveUnBookable(farmId)
                if (response.isSuccessful) {
                    response.body()?.let { result ->
                        _unBookable.postValue(result)
                    }
                } else {
                    Log.e("getReserveUnBookableMessage:","${response.body()?.message}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getFarmerPhoneNumber(farmId: Int) {
        viewModelScope.launch {
            try {
                val response = farmRepo.getFarmerPhoneNumber(farmId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _farmerPhoneNumber.postValue(response.body())
                    }
                } else {
                    Log.e("getFarmPhoneNumber:","${response.body()}")
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}