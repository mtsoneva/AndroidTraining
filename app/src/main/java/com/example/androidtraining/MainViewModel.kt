package com.example.androidtraining

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtraining.models.LoginResponse
import com.example.androidtraining.models.Product
import com.example.androidtraining.models.UserInfo
import com.example.androidtraining.networking.TokenProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val firstRepository: FirstRepository) :
    ViewModel() {
    @Inject
    lateinit var tokenProvider: TokenProvider
    private val _response = MutableStateFlow<Result<LoginResponse>?>(null)
    val response: StateFlow<Result<LoginResponse>?> = _response

    private val _successfulLogin = MutableStateFlow<Boolean?>(null)
    val successfulLogin: StateFlow<Boolean?> = _successfulLogin

    private val _isLoading = MutableStateFlow(false)
    var isLoading: StateFlow<Boolean> = _isLoading

    fun login(userInfo: UserInfo) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val loginResponse = firstRepository.login(userInfo)
                tokenProvider.setJwtToken(loginResponse.jwt)
                _response.value = Result.success(loginResponse)
                _successfulLogin.value = true
            } catch (e: Exception) {
                _response.value = Result.failure(e)
                _successfulLogin.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }

    private val _productResponse = MutableStateFlow<Result<Product>?>(null)
    val productResponse: StateFlow<Result<Product>?> = _productResponse

    private val _isProductLoading = MutableStateFlow(false)
    val isProductLoading: StateFlow<Boolean> = _isLoading

    private val _successfulProductLoading = MutableStateFlow<Boolean?>(null)
    val successfulProductLoading: StateFlow<Boolean?> = _successfulProductLoading

    fun getProduct() {
        viewModelScope.launch {
            try {
                _isProductLoading.value = true
                val getProductDetailsResponse = firstRepository.getProduct()
                _productResponse.value = Result.success(getProductDetailsResponse)
                _successfulProductLoading.value = true
            } catch (e: Exception) {
                _productResponse.value = Result.failure(e)
                _successfulProductLoading.value = false
            } finally {
                _isProductLoading.value = false
            }
        }
    }
}