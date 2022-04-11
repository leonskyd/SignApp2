package com.example.signapp.ui.signUpScreen

import com.example.signapp.data.Repository
import com.example.signapp.domain.SignUpInteractor

class SignUpPresenter(
    private val signUpInteractor: SignUpInteractor
) {
    private val repository = Repository()
    private var view: SignUpView? = null

    fun onAttach(view: SignUpView) {
        this.view = view
    }

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

    fun checkNewLogin(login: String) {
        signUpInteractor.checkLoginBusy(login) { loginBusy ->
            if (loginBusy) {
                view?.setLoginIsBusy()
            } else {
                view?.setOneMoreNewLogin()
            }
        }
    }
}