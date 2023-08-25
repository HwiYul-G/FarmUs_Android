package com.farm.farmus_application.model.farm.list

import com.google.gson.annotations.SerializedName

data class Pictures(
    @SerializedName("Picture_url" ) val PictureUrl : String?,
    @SerializedName("Picture_key" ) val PictureKey : String?
)