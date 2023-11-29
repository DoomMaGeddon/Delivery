package com.luismipalos.delivery.signup.data.network

import android.util.Log
import com.luismipalos.delivery.signup.data.network.dto.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignUpService @Inject constructor(private val client: SignUpClient) {
    suspend fun doSignUp(name: String, user: String, password: String) : Boolean {
        return withContext(Dispatchers.IO) {
            val response = client.doSignUp(UserDTO(name, user, password))

            Log.i("Sign up: ",
                response.body()?.confirmation ?: ("failed, " + UserDTO(name, user, password))
            )
            !response.body()?.confirmation.isNullOrEmpty()
        }
    }
}
