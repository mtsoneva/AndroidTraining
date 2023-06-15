package com.example.androidtraining.repository

import com.example.androidtraining.models.LoginResponse
import com.example.androidtraining.models.Product
import com.example.androidtraining.models.UserInfo
import com.example.androidtraining.networking.API
import com.example.androidtraining.networking.ApiHelper

class FirstRepository(private val apiService: API): ApiHelper {
    override suspend fun login(userInfo: UserInfo): LoginResponse {
        return apiService.login(userInfo)
    }

    override suspend fun getProduct(): Product {
        return apiService.getProduct()
    }
}