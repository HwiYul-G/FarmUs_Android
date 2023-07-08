package com.example.farmus_application.viewmodel.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmus_application.model.farm.list.ListResult
import com.example.farmus_application.repository.farm.FarmRepository
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    private val farmRepo = FarmRepository()
    val farmListResponse: MutableLiveData<List<ListResult>> = MutableLiveData()

    fun getFarmList(email: String) {
        viewModelScope.launch {
            try {
                val response = farmRepo.getFarmList(email)
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.isSuccess) {
                            Log.d("FarmList Success : ", response.body().toString())
                            val farmList = mutableListOf<ListResult>()
                            for (item in it.result) {
                                farmList.add(
                                    ListResult(
                                        item.FarmID,
                                        item.Name,
                                        item.Price,
                                        item.SquaredMeters,
                                        item.Views,
                                        item.Star,
                                        item.Likes,
                                        item.Status,
                                        item.Pictures,
                                        item.Liked
                                    )
                                )
                            }
                            farmListResponse.postValue(farmList)
                        } else {
                            Log.d("FarmList Success : ", response.body().toString())
                        }
                    }
                } else {
                    Log.d("FarmList Failed : ", response.body().toString())
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}