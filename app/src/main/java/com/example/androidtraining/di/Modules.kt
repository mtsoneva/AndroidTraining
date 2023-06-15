package com.example.androidtraining.di

import com.example.androidtraining.repository.FirstRepository
import com.example.androidtraining.networking.API
import com.example.androidtraining.networking.APIClient
import com.example.androidtraining.networking.TokenInterceptor
import com.example.androidtraining.networking.TokenProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
object NetworkingModules {
    @Provides
    @Singleton
    fun providesJWTTokenProvider(): TokenProvider {
        return TokenProvider()
    }

    @Provides
    @Singleton
    fun providesJWTInterceptor(jwtTokenProvider: TokenProvider): TokenInterceptor {
        return TokenInterceptor(jwtTokenProvider)
    }

    @Provides
    @Singleton
    fun providesAPI(providesJWTInterceptor: TokenInterceptor): API {
        return APIClient(providesJWTInterceptor).defaultService
    }

    @Provides
    @Singleton
    fun providesRepository(apiService: API) = FirstRepository(apiService)
}
