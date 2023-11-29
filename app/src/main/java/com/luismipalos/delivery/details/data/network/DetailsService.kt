package com.luismipalos.delivery.details.data.network

import android.util.Log
import javax.inject.Inject

class DetailsService @Inject constructor(private val client: DetailsClient) {
    suspend fun getDetails() : List<String> {
        val details = client.getDetails()

        Log.i("Restaurantes: ", details.toString())

        return listOf(details.body()?.description ?: "")
    }
}