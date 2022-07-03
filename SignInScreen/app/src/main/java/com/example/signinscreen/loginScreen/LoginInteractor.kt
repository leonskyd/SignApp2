package com.example.signinscreen.loginScreen

import model.Repository

class LoginInteractor {
    val repository = Repository()

    fun isLoginExist(login: String): Boolean {
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
}