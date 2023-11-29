package com.luismipalos.delivery.details.domain

import com.luismipalos.delivery.details.data.DetailsRepository
import javax.inject.Inject

class DetailsUseCase @Inject constructor(
    private val networkRepository: DetailsRepository
) {
    suspend operator fun invoke() : List<String> =
        networkRepository.getDetails()
}