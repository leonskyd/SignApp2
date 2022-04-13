package com.example.signapp.data

sealed class AppState {
    data class Success(val isSuccess: Boolean) : AppState()
    data class Error(val isPasswordError: Boolean, val isLoginError: Boolean) : AppState()
    object Loading : AppState()
    object oneMoreLogin : AppState()
}

