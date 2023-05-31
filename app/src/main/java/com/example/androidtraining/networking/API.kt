package com.example.androidtraining.networking

import com.example.androidtraining.models.LoginResponse
import com.example.androidtraining.models.Product
import com.example.androidtraining.models.UserInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface API {
    @POST(value = "/api/auth/local")
    @Headers("No-Authentication: true")
    suspend fun login(@Body userInfo: UserInfo): LoginResponse

    @GET(value = "/api/products/2?populate=*")
    suspend fun getProduct() : Product
}