package com.example.signapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.signapp.data.AppState
import com.example.signapp.data.LoginInteractor


class LoginViewModel(
    private val loginInteractor: LoginInteractor
) : ViewModel() {

    private val state: MutableLiveData<AppState> = MutableLiveData()

    fun onLogin(login: String, password: String) : LiveData<AppState> {
        loginInteractor.login(login, password) { passwordChecked ->
            if (passwordChecked) {
                //view?.setSuccess()
                state.postValue(AppState.Success(true))
            } else {
                state.postValue(AppState.PasswordError(true)) // выходит сообщение об ошибке
            }
        }
        return state
    }

    fun checkLogin(login: String): LiveData<AppState> {
        loginInteractor.checkLogin(login) { loginFound ->
            if (!loginFound) {
                state.postValue(AppState.LoginError(true))
            } else {
                state.postValue(AppState.oneMoreLogin)
            }
        }
        return state
    }

}