package com.luismipalos.delivery.homescreen.data.network

import android.util.Log
import com.luismipalos.delivery.homescreen.data.network.dto.Restaurant
import javax.inject.Inject

class HomeService @Inject constructor(private val client: HomeClient) {
    suspend fun getRestaurants() : List<Restaurant> {
        val restaurantes = client.getRestaurants()

        Log.i("Restaurantes: ", restaurantes.size.toString())
        Log.i("Restaurantes: ", restaurantes.toString())

        return restaurantes
    }
}