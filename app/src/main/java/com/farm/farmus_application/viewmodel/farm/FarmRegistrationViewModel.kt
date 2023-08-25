package com.farm.farmus_application.viewmodel.farm

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farm.farmus_application.model.farm.postings.PostingsResult
import com.farm.farmus_application.repository.farm.FarmRepository
import kotlinx.coroutines.launch
import java.io.File

class FarmRegistrationViewModel() : ViewModel() {
    private val TAG = "FarmRegistrationViewModel"

    private val farmRepository = FarmRepository()

    private var _postingsResponse = MutableLiveData<PostingsResult?>()
    var postingsResponse: LiveData<PostingsResult?> = _postingsResponse

    private var _postingsErrorResponse = MutableLiveData<String?>()
    var postingsErrorResponse: LiveData<String?> = _postingsErrorResponse

    private var _urisCount = MutableLiveData<Int>()
    var urisCount: LiveData<Int> = _urisCount

    private lateinit var _farmTitle: String
    private lateinit var _farmIntroduction: String
    private lateinit var _farmPictures: List<File>
    private lateinit var _farmPicturesUri: MutableList<Uri>

    fun notifyRecyclerviewChange(urisCount: Int) {
        viewModelScope.launch {
            _urisCount.postValue(urisCount)
        }
    }

    fun saveFirstFragmentData(farmTitle: String, farmIntroduction: String, farmPictures: List<File>, farmPicturesUri: MutableList<Uri>) {
        viewModelScope.launch {
            _farmTitle = farmTitle
            _farmIntroduction = farmIntroduction
            _farmPictures = farmPictures
            _farmPicturesUri = farmPicturesUri
        }
    }

    fun getFarmTitle(): String {
        return if (::_farmTitle.isInitialized) _farmTitle else ""
    }

    fun getFarmIntroduction(): String {
        return if (::_farmIntroduction.isInitialized) _farmIntroduction else ""
    }

    fun getFarmPictures(): List<File> {
        return _farmPictures
    }

    fun getFarmPicturesUri(): MutableList<Uri> {
        return if (::_farmPicturesUri.isInitialized) _farmPicturesUri else mutableListOf()
    }

    fun postFarmPostings(
        name: String,
        owner: String,
        startDate: String,
        endDate: String,
        price: String,
        squaredMeters: String,
        locationBig: String,
        locationMid: String,
        locationSmall: String = "",
        description: String,
        category: String = "",
        tag: String = "",
        imageFiles: List<File>
    ) {
        viewModelScope.launch {
            try {
                val response = farmRepository.postFarmPostings(
                    name = name,
                    owner = owner,
                    startDate = startDate,
                    endDate = endDate,
                    price = price,
                    squaredMeters = squaredMeters,
                    locationBig = locationBig,
                    locationMid = locationMid,
                    locationSmall = locationSmall,
                    description = description,
                    category = category,
                    tag = tag,
                    imageFiles = imageFiles
                )

                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.isSuccess) {
                            _postingsResponse.postValue(it.result)
                        } else {
                            _postingsErrorResponse.postValue(it.message)
                        }
                    }
                } else {
                    Log.e(TAG, response.message())
                }
            } catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }

    fun clearAllData() {
        viewModelScope.launch {
            _postingsResponse.postValue(null)
            _postingsErrorResponse.postValue(null)
            _urisCount.postValue(0)
            _farmTitle = ""
            _farmIntroduction = ""
            _farmPictures = listOf()
            _farmPicturesUri = mutableListOf()
        }
    }

}