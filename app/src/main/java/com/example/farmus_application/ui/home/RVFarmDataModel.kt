package com.example.farmus_application.ui.home

data class RVFarmDataModel (
    // img URL의 타입 확인 필요
    var farm_image: String?,
    var farm_name: String,
    var farm_size: String,
    var farm_price: String
)