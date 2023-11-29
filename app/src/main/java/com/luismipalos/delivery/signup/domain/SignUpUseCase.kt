package com.luismipalos.delivery.signup.domain

import com.luismipalos.delivery.core.security.PasswordHash
import com.luismipalos.delivery.signup.data.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val networkRepository: SignUpRepository,
    private val cryptoHash: PasswordHash
) {
    suspend operator fun invoke(name: String, user: String, password: String) : Boolean =
        networkRepository.signUp(name, user, password)
}