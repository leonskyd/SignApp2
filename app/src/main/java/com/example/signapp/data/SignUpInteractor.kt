package com.example.signapp.data

import android.os.Handler

class SignUpInteractor(
    private val repository: RepositoryInterface,
    private val uiHandler: Handler
) : SignUpInteractorInterface {

    override fun checkLoginBusy(login: String, callback: (Boolean) -> Unit) {
        Thread {
            val userList = repository.loadUsers()
            var loginBusy = false
            for (item in userList) {
                if (item.userLogin.equals(login)) {
                    loginBusy = true
                    break
                }
            }
            uiHandler.post {
                callback(loginBusy)
            }
        }.start()
    }
}