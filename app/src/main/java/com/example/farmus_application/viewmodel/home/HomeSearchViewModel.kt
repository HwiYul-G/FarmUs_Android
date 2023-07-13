package com.example.farmus_application.viewmodel.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmus_application.model.farm.search.SearchedFarm
import com.example.farmus_application.repository.farm.FarmRepository
import kotlinx.coroutines.launch

class HomeSearchViewModel : ViewModel() {

    private val farmRepo = FarmRepository()
    val searchedFarmResponse : MutableLiveData<List<SearchedFarm>> = MutableLiveData()


    fun getFarmSearchKeyword(keyword : String) {
        viewModelScope.launch {
            try {
                val response = farmRepo.getFarmSearchKeyword(keyword)
                if(response.isSuccessful){
                    response.body()?.let {
                        if(it.result){
                            Log.d("FarmSearch Success : ", response.body().toString())
                            val serachedFarmList = mutableListOf<SearchedFarm>()
                            for(farm in it.farms){
                                serachedFarmList.add(
                                    SearchedFarm(
                                        farm.FarmID,
                                        farm.Name,
                                        farm.Price,
                                        farm.SquaredMeters,
                                        farm.LocationBig,
                                        farm.LocationMid,
                                        farm.LocationSmall,
                                        farm.Likes,
                                        farm.Picture_url)
                                )
                            }
                            searchedFarmResponse.postValue(serachedFarmList)
                        }else{
                            Log.d("FarmSearch Success : ", response.body().toString())
                        }
                    }
                }else{
                    Log.d("FarmSearch Failed : ", response.body().toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}