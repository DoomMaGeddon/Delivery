package com.luismipalos.delivery.homescreen.data.network.response

import com.google.gson.annotations.SerializedName

data class HomeResponse (
    @SerializedName("cover_url") val coverURL: String
)