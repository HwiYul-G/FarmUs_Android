package com.farm.farmus_application.model.farm.myfarm

data class MyFarmRes(
    val myFarmList: List<MyFarmItem>,
    val result: Boolean,
    val message: String?
)