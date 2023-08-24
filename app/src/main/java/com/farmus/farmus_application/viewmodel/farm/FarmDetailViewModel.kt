package com.farmus.farmus_application.viewmodel.farm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farmus.farmus_application.model.farm.detail.DetailResult
import com.farmus.farmus_application.repository.farm.FarmRepository
import kotlinx.coroutines.launch

class FarmDetailViewModel(): ViewModel() {

    private val farmRepo = FarmRepository()

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
}