package com.example.signapp.domain

import com.example.signapp.data.Repository
import android.os.Handler
import com.example.signapp.data.RepositoryInterface

class LoginInteractor(
    private val repository: RepositoryInterface,
    private val uiHandler: Handler
): LoginInteractorInterface {

    override fun login(login: String, password: String, callback: (Boolean) -> Unit) {
        Thread {
            var passwordChecked = false
            val userList = repository.loadUsers()
            for (item in userList) {
                if (item.userLogin.equals(login)
                    && item.userPassword.equals(password)
                ) {
                    passwordChecked = true
                }
            }
            uiHandler.post {
                callback(passwordChecked)
            }
        }.start()
    }

    override fun checkLogin(login: String, callback: (Boolean) -> Unit) {
        Thread {
            val userList = repository.loadUsers()
            var loginFound = false
            for (item in userList) {
                if (item.userLogin.equals(login)) {
                    loginFound = true
                    break
                }
            }
            uiHandler.post {
                callback(loginFound)
            }
        }.start()
    }

}