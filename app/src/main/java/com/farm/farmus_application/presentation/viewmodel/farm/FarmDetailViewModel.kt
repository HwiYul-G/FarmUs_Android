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
class FarmDetailViewModel @Inject constructor(
    private val farmRepo: FarmRepository
): ViewModel() {
    private val TAG = "FarmDetailViewModel"

    private var _farmDetail = MutableLiveData<DetailResult>()
    var farmDetail: LiveData<DetailResult> = _farmDetail

    fun getFarmDetail(farmId: Int) {
        viewModelScope.launch {
            try {
                val response = farmRepo.getFarmDetail(farmId)
                if (response.isSuccessful) {
                    response.body()?.let { detailRes ->
                        _farmDetail.postValue(detailRes.result)
                    }
                } else {
                    Log.e("getFarmDetailResponseCode:", response.body().toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun saveTempFarmDetail(farmDetail: DetailResult?) {
        viewModelScope.launch {
            farmRepo.saveTempFarmDetail(farmDetail)
        }
    }
}