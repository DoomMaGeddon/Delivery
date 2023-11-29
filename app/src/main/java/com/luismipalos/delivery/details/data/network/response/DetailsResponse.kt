package com.luismipalos.delivery.details.data.network.response

import com.google.gson.annotations.SerializedName

data class DetailsResponse (
    @SerializedName("description") val description: String,
    @SerializedName("ingredients") val ingredients: List<String>
)