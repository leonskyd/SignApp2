package com.example.signapp.data

import androidx.annotation.MainThread

interface SignUpInteractorInterface {
    fun checkLoginBusy(
        login: String,
        @MainThread callback: (Boolean) -> Unit
    )
}