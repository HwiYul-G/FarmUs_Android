package com.farm.farmus_application.data.farm.remote.dto.list

import com.google.gson.annotations.SerializedName

data class Pictures(
    @SerializedName("Picture_url" ) val PictureUrl : String?,
    @SerializedName("Picture_key" ) val PictureKey : String?
)