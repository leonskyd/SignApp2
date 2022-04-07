package com.example.signapp.model

interface RepositoryInterface {
    fun loadUsers(): List<User>
    fun addUser(login: String, password: String)

}