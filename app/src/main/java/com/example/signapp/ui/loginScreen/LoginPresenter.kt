package com.example.signapp.ui.loginScreen

import com.example.signapp.domain.LoginInteractor

class LoginPresenter(
    private val loginInteractor: LoginInteractor
) {
    private var view: LoginView? = null

    fun onAttach(view: LoginView) {
        this.view = view
    }

    fun onLogin(login: String, password: String) {
        loginInteractor.login(login, password) { passwordChecked ->
            if (passwordChecked) {
                view?.setSuccess()
            } else {
                view?.setPasswordError() // выходит сообщение об ошибке
            }
        }
    }

    fun checkLogin(login: String) {
        loginInteractor.checkLogin(login) { loginFound ->
            if (!loginFound) {
                view?.setLoginError()
            } else {
                view?.setOneMoreLogin()
            }
        }
    }
}
