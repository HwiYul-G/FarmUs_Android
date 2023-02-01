package com.example.farmus_application.ui.home

data class FilterDataModel (
        val loc_city: String ="",
        val loc_town: String = "",

        val price_start: Int = 0,
        val price_end: Int = 0,

        val area : String = "",

        val date_start : String = "",
        val date_End : String = ""
        )