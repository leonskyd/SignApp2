package com.example.signapp.data

sealed class AppState {
    data class Success(val isSuccess: Boolean) : AppState()
    data class PasswordError(val isPasswordError: Boolean) : AppState()
    data class LoginError(val isLoginError: Boolean) : AppState()
    object Loading : AppState()
    object oneMoreLogin : AppState()
}

