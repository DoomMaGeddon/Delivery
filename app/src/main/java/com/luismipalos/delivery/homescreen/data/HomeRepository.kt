package com.luismipalos.delivery.homescreen.data

import com.luismipalos.delivery.homescreen.data.network.HomeService
import com.luismipalos.delivery.homescreen.data.network.response.DishResponse
import com.luismipalos.delivery.homescreen.data.network.response.RestaurantResponse
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: HomeService) {
    suspend fun getRestaurants() : List<RestaurantResponse> = api.getRestaurants()
    suspend fun getDishes() : List<DishResponse> = api.getDishes()
}