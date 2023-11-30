package com.luismipalos.delivery.details.data

import com.luismipalos.delivery.details.data.network.DetailsService
import com.luismipalos.delivery.details.data.network.response.DetailsResponse
import javax.inject.Inject

class DetailsRepository @Inject constructor(private val api: DetailsService) {
    suspend fun getDetails(name: String) : DetailsResponse? = api.getDetails(name)
}