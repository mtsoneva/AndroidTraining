package com.example.androidtraining.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient(private val jwtInterceptor: TokenInterceptor) {
    private val DEFAULT_TIMEOUT = 1L

    val defaultService: API

    init {
        defaultService = createService()
    }

    // User and Auth service
    private fun createService(): API {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
            .addInterceptor(jwtInterceptor)
            .build()

        val client = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://ethereal-artefacts.fly.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return client.create(API::class.java)
    }

}