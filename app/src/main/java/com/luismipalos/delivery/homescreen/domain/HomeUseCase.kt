package com.luismipalos.delivery.homescreen.domain

import com.luismipalos.delivery.homescreen.data.HomeRepository
import com.luismipalos.delivery.homescreen.data.network.response.RestaurantResponse
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val networkRepository: HomeRepository
) {
    suspend operator fun invoke() : List<RestaurantResponse> {
        val restaurants = networkRepository.getRestaurants()
        val dishes = networkRepository.getDishes()

        for (i in 1..4) {
            restaurants[i].dish = dishes[i]
        }

        return listOf(
            restaurants[0],
            restaurants[1],
            restaurants[2],
            restaurants[3]
        )
    }
}