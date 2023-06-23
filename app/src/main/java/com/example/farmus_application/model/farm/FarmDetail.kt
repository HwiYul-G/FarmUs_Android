package com.example.farmus_application.model.farm

//data class FarmDetail (
//        val category: String,
//        val description: String,
//        val location: String,
//        val name: String,
//        val owner: String,
//        val picture_url: String,
//        val price: Int,
//        val squaredMeters: Int,
//        val tag: String,
//        val term: String
//)

data class FarmDetail(
    val name: String,
    val owner: String,
    val term: String,
    val price: Int,
    val squareMeters: Int,
    val location: String,
    val description: String,
    val picture_url: String,
    val category: String,
    val tage: String
)
