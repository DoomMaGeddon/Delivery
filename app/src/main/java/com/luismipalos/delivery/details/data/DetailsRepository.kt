package com.luismipalos.delivery.details.data

import com.luismipalos.delivery.details.data.network.DetailsService
import javax.inject.Inject

class DetailsRepository @Inject constructor(private val api: DetailsService) {
    suspend fun getDetails() : List<String> = api.getDetails()
}