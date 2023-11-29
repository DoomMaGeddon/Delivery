package com.luismipalos.delivery.signup.data

import com.luismipalos.delivery.signup.data.network.SignUpService
import javax.inject.Inject

class SignUpRepository @Inject constructor(private val api: SignUpService) {
    suspend fun signUp(name: String, user: String, password: String) : Boolean =
        api.doSignUp(name, user, password)
}