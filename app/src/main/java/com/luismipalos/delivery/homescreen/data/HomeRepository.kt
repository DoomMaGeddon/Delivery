package com.luismipalos.delivery.homescreen.data

import com.luismipalos.delivery.homescreen.data.network.HomeService
import com.luismipalos.delivery.homescreen.data.network.dto.Restaurant
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: HomeService) {
    suspend fun getRestaurants() : List<Restaurant> = api.getRestaurants()
}