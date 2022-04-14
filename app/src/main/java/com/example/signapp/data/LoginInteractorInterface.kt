package com.example.signapp.data

import androidx.annotation.MainThread

interface LoginInteractorInterface {

    fun login (
        login:String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    )

    fun checkLogin (login:String,
    @MainThread callback: (Boolean) -> Unit)
}