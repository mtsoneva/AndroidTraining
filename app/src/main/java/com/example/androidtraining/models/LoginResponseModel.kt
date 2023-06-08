package com.example.androidtraining.models

data class LoginResponse(val jwt: String, val user: LoggedUserModel)

data class LoggedUserModel(val id: String, val username: String, val email: String)

data class UserInfo (val identifier: String, val password: String)