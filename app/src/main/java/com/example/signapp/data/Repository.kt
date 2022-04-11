package com.example.signapp.data

class Repository: RepositoryInterface {

    // Эмуляция загрузки пользователей из БД
    override fun loadUsers(): MutableList<User> {
        return arrayListOf(
            User(1,"petrov", "12345"),
            User(2,"ivanov","23456"),
            User(3,"mitin","34567"),
            User(4,"veselov","45678"),
            User(5,"golovin","56789"),
            User(6,"behterev","67891"),
            User(7,"simkin","78912")
        )
    }
    // будет добавлять новых пользователей в БД
    override fun addUser(login: String, password:String) {
        var users = loadUsers()
        var id = users.size + 1
        users.add(User(id, login, password))
    }
}