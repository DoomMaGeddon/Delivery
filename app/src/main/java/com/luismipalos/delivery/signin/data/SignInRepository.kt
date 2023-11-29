package com.luismipalos.delivery.signin.data

import com.luismipalos.delivery.signin.data.network.SignInService
import javax.inject.Inject

class SignInRepository @Inject constructor(private val api: SignInService) {
    suspend fun signIn(user: String, password: String) : Boolean = api.doSignIn(user, password)
}