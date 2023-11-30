package com.luismipalos.delivery.homescreen.data.network.response

import com.google.gson.annotations.SerializedName

data class DishResponse (
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("ingredients") val ingredients: List<String>,
    @SerializedName("cover_url") val coverUrl: String
)