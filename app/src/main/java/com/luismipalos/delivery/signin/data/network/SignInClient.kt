package com.luismipalos.delivery.signin.data.network

import com.luismipalos.delivery.signin.data.network.dto.UserDTO
import com.luismipalos.delivery.signin.data.network.response.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignInClient {
    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNsdXRydHFjZWVyZ2FmZHRxamViIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDAwNTI2MDksImV4cCI6MjAxNTYyODYwOX0.M0LOr8aHu4GA8TE9B-7skWZl348m2YF1ACUX_CZQcBw",
        "Content-Type: application/json"
    )
    @POST("auth/v1/token?grant_type=password")
    suspend fun doSignIn(@Body user: UserDTO) : Response<SignInResponse>
}