package com.example.signapp.domain

import androidx.annotation.MainThread

interface SignUpInteractorInterface {
    fun checkLoginBusy(
        login: String,
        @MainThread callback: (Boolean) -> Unit
    )
}