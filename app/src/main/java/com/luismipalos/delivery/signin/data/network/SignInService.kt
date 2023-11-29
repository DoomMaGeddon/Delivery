package com.luismipalos.delivery.signin.data.network

import com.luismipalos.delivery.signin.data.network.dto.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import android.util.Log

class SignInService @Inject constructor(private val client: SignInClient) {
    suspend fun doSignIn(user: String, password: String) : Boolean {
        return withContext(Dispatchers.IO) {
            val response = client.doSignIn(UserDTO(user, password))

            Log.i("Sign in: ", response.body()?.accessToken ?: "failed")
            !response.body()?.accessToken.isNullOrEmpty()
        }
    }
}