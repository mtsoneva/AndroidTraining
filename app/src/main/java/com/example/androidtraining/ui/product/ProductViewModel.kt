package com.example.androidtraining.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtraining.repository.FirstRepository
import com.example.androidtraining.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val firstRepository: FirstRepository) :
    ViewModel() {

    private val _productResponse = MutableStateFlow<Result<Product>?>(null)
    val productResponse: StateFlow<Result<Product>?> = _productResponse

    private val _isProductLoading = MutableStateFlow(false)
    val isProductLoading: StateFlow<Boolean> = _isProductLoading

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