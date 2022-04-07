package com.example.signapp.loginScreen

interface LoginView {

    fun setSuccess()
    fun setLoginError()
    fun setPasswordError()
    fun setLoading()
    fun setOneMoreLogin()
}