package com.luismipalos.delivery.homescreen.data.network.response

import com.google.gson.annotations.SerializedName

data class RestaurantResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("address") val address: String,
    @SerializedName("cover_url") val coverURL: String,
    var dish: DishResponse
)