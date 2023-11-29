package com.luismipalos.delivery.signin.data.network.response

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("expires_in") val expiresIn: String,
    @SerializedName("expires_at") val expiresAt: String,
    @SerializedName("refresh_token") val refreshToken: String
)