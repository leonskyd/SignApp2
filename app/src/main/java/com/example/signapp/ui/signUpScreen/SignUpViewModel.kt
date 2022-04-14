package com.example.signapp.ui.signUpScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.signapp.data.AppState
import com.example.signapp.data.Repository
import com.example.signapp.data.SignUpInteractor

class SignUpViewModel (
    private val signUpInteractor: SignUpInteractor
) : ViewModel() {
    private val repository = Repository()
    private val state: MutableLiveData<AppState> = MutableLiveData()

    fun onSignup(login: String, password: String) {
        repository.addUser(login, password)
    }

    fun isPasswordConfirmed(password: String, repeatPassword: String): Boolean {
        var confirmed = false
        if (password.equals(repeatPassword)) {
            confirmed = true
        }
        return confirmed
    }

    fun checkNewLogin(login: String): LiveData<AppState> {
        signUpInteractor.checkLoginBusy(login) { loginBusy ->
            if (loginBusy) {
                state.postValue(AppState.LoginBusy(true))
            } else {
                state.postValue(AppState.OneMoreNewLogin)
            }
        }
        return state
    }
}