package com.example.farmus_application.viewmodel.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmus_application.repository.farm.FarmRepository
import com.example.farmus_application.ui.home.RVFarmDataModel
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    private val farmRepo = FarmRepository()
    val farmListResponse : MutableLiveData<List<RVFarmDataModel>> = MutableLiveData()

    fun getFarmList(){
        viewModelScope.launch {
            try{
                val response = farmRepo.getFarmList()
                if(response.isSuccessful){
                    response.body()?.let{
                        if(it.isSuccess){
                            Log.d("FarmList Success : ", response.body().toString())
                            val farmList = mutableListOf<RVFarmDataModel>()
                            for(item in it.result){
                                // item의 면적이 제곱으로 날라오므로 미리 평수 면적 처리
                                val squaredMeter = item.SquaredMeters
                                val squaredFeet = squaredMeter * 0.3025
                                val itemSize = squaredFeet.toString() + "평 ("+squaredMeter+"㎡)"
                                farmList.add(RVFarmDataModel(item.Picture_url, item.Name, itemSize, item.Price.toString()))
                            }
                            farmListResponse.postValue(farmList)
                        }else{
                            Log.d("FarmList Success : ", response.body().toString())
                        }
                    }
                }else{
                    Log.d("FarmList Failed : ", response.body().toString())
                }

            }catch(e: Exception){
                e.printStackTrace()
            }
        }
    }

}