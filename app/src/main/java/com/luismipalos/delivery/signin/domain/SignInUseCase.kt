package com.luismipalos.delivery.signin.domain

import com.luismipalos.delivery.core.security.PasswordHash
import com.luismipalos.delivery.signin.data.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val networkRepository: SignInRepository,
    private val cryptoHash: PasswordHash
) {
    suspend operator fun invoke(user: String, password: String) : Boolean =
        networkRepository.signIn(user, password)
}