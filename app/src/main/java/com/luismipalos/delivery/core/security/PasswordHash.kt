package com.luismipalos.delivery.core.security

import android.util.Base64
import java.security.SecureRandom
import java.security.spec.KeySpec
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class PasswordHash {
    private fun hashPassword(password: CharArray, salt: ByteArray): String {
        val iterationCount = 10000
        val keyLength = 256
        val keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        val keySpec: KeySpec = PBEKeySpec(password, salt, iterationCount, keyLength)
        val secretKey = keyFactory.generateSecret(keySpec)
        return secretKey.encoded.joinToString("") { "%02x".format(it) }
    }

    private fun generateSalt(): ByteArray {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return salt
    }

    private fun verifyPassword(
        plainPassword: CharArray,
        hashedPassword: String,
        salt: ByteArray
    ): Boolean {
        val newlyHashedPassword = hashPassword(plainPassword, salt)
        return hashedPassword == newlyHashedPassword
    }

    fun getCryptoPassword(password: String): Pair<String, String> {
        val salt = generateSalt()
        val hash = hashPassword(password.toCharArray(), salt)
        return Pair(hash, Base64.encodeToString(salt, Base64.DEFAULT))
    }
}