package com.example.androidtraining.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtraining.repository.FirstRepository
import com.example.androidtraining.models.LoginResponse
import com.example.androidtraining.models.UserInfo
import com.example.androidtraining.networking.TokenProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val firstRepository: FirstRepository) :
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
}