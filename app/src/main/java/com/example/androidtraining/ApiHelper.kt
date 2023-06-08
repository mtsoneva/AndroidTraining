package com.example.androidtraining

import com.example.androidtraining.models.LoginResponse
import com.example.androidtraining.models.Product
import com.example.androidtraining.models.UserInfo

interface ApiHelper {
    suspend fun login(userInfo: UserInfo): LoginResponse
    suspend fun getProduct(): Product
}