package com.example.signapp.loginScreen

import android.os.SystemClock.sleep
import com.example.signapp.model.Repository

class LoginPresenter {

    private val repository = Repository()
    private var view: LoginView? = null


    fun onAttach(view: LoginView) {
        this.view = view
    }

    fun onLogin(login: String, password: String) {
        if(checkPassword(login, password)) {
            view?.setSuccess()
        } else {
            view?.setPasswordError() // выходит сообщение об ошибке
        }
    }

    fun checkPassword(login: String, password: String): Boolean {
        var passwordChecked = false
        val userList = repository.loadUsers()
        for (item in userList) {
            if (item.userLogin.equals(login)
                && item.userPassword.equals(password)
            ) {
                passwordChecked = true
            }
        }
        return passwordChecked
    }


        fun checkLogin(login: String) {
            if (!isLoginExist(login)) {
                view?.setLoginError()
            } else {
                view?.setOneMoreLogin()
            }
        }

        private fun isLoginExist(login: String): Boolean {
            val userList = repository.loadUsers()
            var found = false
            for (item in userList) {
                if (item.userLogin.equals(login)) {
                    found = true
                    break
                }
            }
            return found
        }
    }
