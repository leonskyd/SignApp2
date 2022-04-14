package com.example.signapp.data

import androidx.annotation.WorkerThread
import com.example.signapp.domain.User

interface RepositoryInterface {
    @WorkerThread
    fun loadUsers(): List<User>
    @WorkerThread
    fun addUser(login: String, password: String)

}