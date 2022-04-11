package com.example.signapp.ui.loginScreen

interface LoginView {

    fun setSuccess()
    fun setLoginError()
    fun setPasswordError()
    fun setLoading()
    fun setOneMoreLogin()
}