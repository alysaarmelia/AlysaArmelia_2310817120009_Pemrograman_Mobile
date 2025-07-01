package com.example.myapi_test.data.util

import java.security.MessageDigest

object PasswordHasher {

    fun hashPassword(password: String): String {
        return try {
            val bytes = password.toByteArray()
            val md = MessageDigest.getInstance("SHA-256")
            val digest = md.digest(bytes)
            // Converts the byte array into a hexadecimal string
            digest.fold("") { str, it -> str + "%02x".format(it) }
        } catch (e: Exception) {

            e.printStackTrace()
            ""
        }
    }
}
