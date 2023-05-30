package com.example.farmus_application.ui.farm

data class FarmDataModel (
    val image : Int,
    val title: String,
    val startDay: String,
    val endDay: String
        )

data class MyFarmDataModel (
    val image : Int,
    val location : String,
    val title: String,
    val size : String
        )