package com.gdsc.boolpyeon.user

interface UserService {
    fun signup()

    fun login()

    fun logout()

    fun getUser(userId: Int): User
}