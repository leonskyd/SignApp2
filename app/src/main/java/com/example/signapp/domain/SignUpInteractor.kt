package com.example.signapp.domain

import android.os.Handler
import com.example.signapp.data.RepositoryInterface

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