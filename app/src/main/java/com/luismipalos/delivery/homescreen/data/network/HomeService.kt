package com.luismipalos.delivery.homescreen.data.network

import android.util.Log
import com.luismipalos.delivery.homescreen.data.network.response.DishResponse
import com.luismipalos.delivery.homescreen.data.network.response.RestaurantResponse
import javax.inject.Inject

class HomeService @Inject constructor(private val client: HomeClient) {
    suspend fun getRestaurants() : List<RestaurantResponse> {
        val restaurants = client.getRestaurants().body()!!

        Log.i("Nº of Restaurants: ", restaurants.size.toString())
        Log.i("Restaurants: ", restaurants.toString())

        return restaurants
    }

    suspend fun getDishes() : List<DishResponse> {
        val dishes = client.getDishes().body()!!

        Log.i("Nº of Dishes: ", dishes.size.toString())
        Log.i("Dishes: ", dishes.toString())

        return dishes
    }
}