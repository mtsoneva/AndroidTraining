package com.example.androidtraining

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtraining.models.LoginResponse
import com.example.androidtraining.models.Product
import com.example.androidtraining.models.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val firstRepository: FirstRepository) :
    ViewModel() {
    private val _response = MutableStateFlow<Result<LoginResponse>?>(null)
    val response: StateFlow<Result<LoginResponse>?> = _response

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun login(userInfo: UserInfo) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val loginResponse = firstRepository.login(userInfo)
                _response.value = Result.success(loginResponse)
            } catch (e: Exception) {
                _response.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    private val _productResponse = MutableStateFlow<Result<Product>?>(null)
    val productResponse: StateFlow<Result<Product>?> = _productResponse

    private val _isProductLoading = MutableStateFlow(false)
    val isProductLoading: StateFlow<Boolean> = _isLoading

    fun getProduct() {
        viewModelScope.launch {
            try {
                _isProductLoading.value = true
                val getProductDetailsResponse = firstRepository.getProduct()
                _productResponse.value = Result.success(getProductDetailsResponse)
            } catch (e: Exception) {
                _productResponse.value = Result.failure(e)
            } finally {
                _isProductLoading.value = false
            }
        }
    }
}