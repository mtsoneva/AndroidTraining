package com.example.androidtraining.networking

class TokenProvider(
    private var jwtToken: String? = null
) {

    fun setJwtToken(token: String) {
        jwtToken = token
    }

    fun getJwtToken(): String? {
        return jwtToken
    }

    fun isLoggedIn(): Boolean {
        jwtToken?.let {
            return true
        }
        return false
    }

    fun clearToken() {
        jwtToken = null
    }
}