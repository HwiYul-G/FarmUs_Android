package com.example.farmus_application.viewmodel.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmus_application.model.favorite.FavoriteFarm
import com.example.farmus_application.model.favorite.FavoriteFarmRes
import com.example.farmus_application.model.user.likes.LikeFarmReq
import com.example.farmus_application.repository.farm.FarmRepository
import com.example.farmus_application.repository.user.UserRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class FavoriteViewModel : ViewModel() {

    private val farmRepo = FarmRepository()
    private val userRepo = UserRepository()


    private val _favoriteFarmResponse : MutableLiveData<FavoriteFarmRes> = MutableLiveData()
    val favoriteFarmResponse : LiveData<FavoriteFarmRes> = _favoriteFarmResponse

    private val _isLikeFarmSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isLikeFarmSuccess: LiveData<Boolean> = _isLikeFarmSuccess
    private val _isDeleteLikeFarmSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isDeleteLikeFarmSuccess: LiveData<Boolean> = _isDeleteLikeFarmSuccess

    fun getFavoriteFarmList(email: String) {
        viewModelScope.launch {
            try {
                val response = farmRepo.getFavoriteFarmList(email)
                Log.d("FavoriteFarmList : ", response.raw().toString())
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.result) {
                            val favoriteFarmList = mutableListOf<FavoriteFarm>()
                            for (item in it.farmList) {
                                favoriteFarmList.add(
                                    FavoriteFarm(
                                        item.FarmID,
                                        item.Name,
                                        item.Price,
                                        item.SquaredMeters,
                                        item.LocationBig,
                                        item.LocationMid,
                                        item.LocationSmall,
                                        item.Likes,
                                        item.PictureUrl
                                    )
                                )
                            }
                            _favoriteFarmResponse.postValue(it)
                        } else {
                            _favoriteFarmResponse.postValue(it)
                            Log.d("FavoriteFarmList result failed : ", response.body().toString())
                        }
                    }
                } else {
                    Log.d("FavoriteFarmList Failed : ", response.body().toString())
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
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.isSuccess) {
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
            try {
                val response = userRepo.deleteUserLikeFarm(email, farmId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.result) {
                            Log.d("DeleteLikeFarm Success : ", response.body().toString())
                            _isDeleteLikeFarmSuccess.postValue(true)
                        } else {
                            Log.d("DeleteLikeFarm Success : ", response.body().toString())
                            _isDeleteLikeFarmSuccess.postValue(false)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}