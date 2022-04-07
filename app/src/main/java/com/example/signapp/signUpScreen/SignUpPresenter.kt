package com.example.signapp.signUpScreen

import com.example.signapp.model.Repository

class SignUpPresenter {
    private val repository = Repository()
    private var view: SignUpView? = null

    fun onAttach(view: SignUpView) {
        this.view = view
    }

    fun onSignup(login: String, password: String) {
        repository.addUser(login, password)
    }

    fun isPasswordConfirmed(password: String, repeatPassword: String) : Boolean{
        var confirmed = false
        if (password.equals(repeatPassword)) {
            confirmed = true
        }
        return confirmed
    }

    fun checkNewLogin(login: String) {
        if( isLoginBusy(login)) {
            view?.setLoginIsBusy()
        } else {
            view?.setOneMoreNewLogin()
        }
    }

    private fun isLoginBusy(login: String): Boolean {
        val userList = repository.loadUsers()
        var busy = false
        for (item in userList) {
            if (item.userLogin.equals(login)){
                busy = true
                break
            }
        }
        return busy
    }
}