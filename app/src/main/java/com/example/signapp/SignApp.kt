package com.example.signapp

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.signapp.data.Repository
import com.example.signapp.data.RepositoryInterface
import com.example.signapp.domain.LoginInteractor
import com.example.signapp.domain.SignUpInteractor

class SignApp : Application() {
    val repo: RepositoryInterface by lazy { Repository() }
    val loginInteractor: LoginInteractor by lazy {
        LoginInteractor(app.repo, Handler(Looper.getMainLooper()))
    }
    val signUpInteractor: SignUpInteractor by lazy {
        SignUpInteractor(app.repo, Handler(Looper.getMainLooper()))
    }
}

val Context.app: SignApp
    get() {
        return applicationContext as SignApp
    }