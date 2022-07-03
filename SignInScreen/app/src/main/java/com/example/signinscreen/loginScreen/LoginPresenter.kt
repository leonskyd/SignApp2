package com.example.signinscreen.loginScreen

class LoginPresenter {

    val loginInteractor = LoginInteractor()
    var view: LoginView? = null


    fun onAttach(view: LoginView) {
        this.view = view
    }

    fun onLogin(login: String, password: String) {
        if(loginInteractor.checkPassword(login, password)) {
            view?.setSuccess()
        } else {
            view?.setPasswordError() // выходит сообщение об ошибке
        }
    }

    fun checkLogin(login: String) {
        if (!loginInteractor.isLoginExist(login)) {
            view?.setLoginError()
        } else {
            view?.setOneMoreLogin()
        }
    }
}