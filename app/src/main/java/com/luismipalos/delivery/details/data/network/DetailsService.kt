package com.luismipalos.delivery.details.data.network

import com.luismipalos.delivery.details.data.network.response.DetailsResponse
import javax.inject.Inject

class DetailsService @Inject constructor(private val client: DetailsClient) {
    suspend fun getDetails(name: String) : DetailsResponse? {
        val details = client.getDetails().body()

        if (details != null) {
            for (item in details) {
                if (item.name == name)
                    return item
            }
        }
        return null
    }
}