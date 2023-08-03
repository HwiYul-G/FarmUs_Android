package com.example.farmus_application.viewmodel.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmus_application.model.farm.list.ListResult
import com.example.farmus_application.model.user.likes.LikeFarmReq
import com.example.farmus_application.repository.farm.FarmRepository
import com.example.farmus_application.repository.user.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val farmRepo = FarmRepository()
    val farmListResponse: MutableLiveData<List<ListResult>> = MutableLiveData()

    private val userRepo = UserRepository()
    private val _isLikeFarmSuccess : MutableLiveData<Boolean> = MutableLiveData()
    val isLikeFarmSuccess : LiveData<Boolean> = _isLikeFarmSuccess
    private val _isDeleteLikeFarmSuccess : MutableLiveData<Boolean> = MutableLiveData()
    val isDeleteLikeFarmSuccess : LiveData<Boolean> = _isDeleteLikeFarmSuccess


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


    fun postLikeFarm(email: String, farmId: Int) {
        viewModelScope.launch {
            try {
                val response = userRepo.postUserLikeFarm(LikeFarmReq(email, farmId))
                if(response.isSuccessful){
                    response.body()?.let {
                        if(it.isSuccess){
                            Log.d("LikeFarm Success : ", response.body().toString())
                            _isLikeFarmSuccess.postValue(true)
                        } else {
                            Log.d("LikeFarm Success : ", response.body().toString())
                            _isLikeFarmSuccess.postValue(false)
                        }
                    }
                } else {
                    Log.d("LikeFarm Failed : ", response.body().toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteLikeFarm(email: String, farmId: Int) {
        viewModelScope.launch {
            try{
                val response = userRepo.deleteUserLikeFarm(email, farmId)
                if(response.isSuccessful){
                    response.body()?.let {
                        if(it.result){
                            Log.d("DeleteLikeFarm Success : ", response.body().toString())
                            _isDeleteLikeFarmSuccess.postValue(true)
                        } else {
                            Log.d("DeleteLikeFarm Success : ", response.body().toString())
                            _isDeleteLikeFarmSuccess.postValue(false)
                        }
                    }
                }
            }catch(e : Exception){
                e.printStackTrace()
            }
        }
    }
}