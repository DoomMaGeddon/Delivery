package com.luismipalos.delivery.homescreen.domain

import com.luismipalos.delivery.homescreen.data.HomeRepository
import com.luismipalos.delivery.homescreen.data.network.dto.Restaurant
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val networkRepository: HomeRepository
) {
    suspend operator fun invoke() : List<Restaurant> =
        networkRepository.getRestaurants()
}