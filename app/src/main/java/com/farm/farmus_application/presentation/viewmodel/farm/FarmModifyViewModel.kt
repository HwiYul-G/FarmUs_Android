package com.farm.farmus_application.presentation.viewmodel.farm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farm.farmus_application.model.farm.detail.DetailResult
import com.farm.farmus_application.repository.farm.FarmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class FarmModifyViewModel @Inject constructor(
    private val farmRepo: FarmRepository
) : ViewModel() {
    private val TAG = "FarmModifyViewModel"

    private var _farmDetail = MutableLiveData<DetailResult>()
    var farmDetail: LiveData<DetailResult> = _farmDetail

    private var _editinfoResponse = MutableLiveData<Boolean?>()
    var editinfoResponse: LiveData<Boolean?> = _editinfoResponse

    private var _editinfoErrorResponse = MutableLiveData<String?>()
    var editinfoErrorResponse: LiveData<String?> = _editinfoErrorResponse

    private var _urisCount = MutableLiveData<Int>()
    var urisCount: LiveData<Int> = _urisCount

    fun patchFarmEditinfo(
        farmId: Int,
        farmName: String,
        farmInfo: String,
        locationBig: String,
        locationMid: String,
        locationSmall : String = "",
        size: String,
        price: String,
        file: List<File>
    ) {
        viewModelScope.launch {
            try {
                val response = farmRepo.patchFarmEditinfo(
                    farmId = farmId,
                    farmName = farmName,
                    farmInfo = farmInfo,
                    locationBig = locationBig,
                    locationMid = locationMid,
                    locationSmall = locationSmall,
                    size = size,
                    price = price,
                    file = file
                )

                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.result) {
                            _editinfoResponse.postValue(it.result)
                            Log.e("xxxxxxxx", it.result.toString())
                        } else {
                            _editinfoErrorResponse.postValue(it.message)
                        }
                    }
                } else {
                    Log.e(TAG, response.message())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTempFileDetail() {
        viewModelScope.launch {
            _farmDetail.postValue(farmRepo.getTempFarmDetail())
        }
    }

    fun notifyRecyclerviewChange(urisCount: Int) {
        viewModelScope.launch {
            _urisCount.postValue(urisCount)
        }
    }

}