package com.farm.farmus_application.data.farm.remote.dto.myfarm

data class MyFarmRes(
    val myFarmList: List<MyFarmItem>,
    val result: Boolean,
    val message: String?
)