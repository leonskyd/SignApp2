package model

interface RepositoryInterface {
    fun loadUsers(): List<User>
    fun addUser(login: String, password: String)

}