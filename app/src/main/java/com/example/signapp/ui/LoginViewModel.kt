package com.example.signapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.signapp.data.AppState
import com.example.signapp.data.LoginInteractor


class LoginViewModel(
    private val loginInteractor: LoginInteractor
) : ViewModel() {

    private val state: MutableLiveData<AppState> = MutableLiveData()

    fun onLogin(login: String, password: String) {
        loginInteractor.login(login, password) { passwordChecked ->
            if (passwordChecked) {
                //view?.setSuccess()
                state.postValue(AppState.Success(true))
            } else {
                state.postValue(AppState.Error(true,false)) // выходит сообщение об ошибке
            }
        }
    }

    fun checkLogin(login: String) {
        loginInteractor.checkLogin(login) { loginFound ->
            if (!loginFound) {
                state.postValue(AppState.Error(false,true))
            } else {
                state.postValue(AppState.oneMoreLogin)
            }
        }
    }
}