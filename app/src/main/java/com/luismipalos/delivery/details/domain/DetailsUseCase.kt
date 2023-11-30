package com.luismipalos.delivery.details.domain

import com.luismipalos.delivery.details.data.DetailsRepository
import com.luismipalos.delivery.details.data.network.response.DetailsResponse
import javax.inject.Inject

class DetailsUseCase @Inject constructor(
    private val networkRepository: DetailsRepository
) {
    suspend operator fun invoke(name: String) : DetailsResponse? =
        networkRepository.getDetails(name)
}