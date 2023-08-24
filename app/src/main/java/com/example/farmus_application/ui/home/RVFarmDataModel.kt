package com.example.farmus_application.ui.home

import com.example.farmus_application.model.farm.list.Pictures
import com.google.gson.annotations.SerializedName


// TODO : 연관된 데이터들 작업 후에 삭제 필요. model/farm/list/ListResult 로 사용.
data class RVFarmDataModel (
    var farm_image: String?,
    var farm_name: String,
    var farm_size: String,
    var farm_price: String

)