package com.gdsc.boolpyeon.user

import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    override fun signup() {
        println("signup")
    }

    override fun login() {
        println("login")
    }

    override fun logout() {
        println("logout")
    }

    override fun getUser(userId: Int): User {
        TODO("실제 유저 정보 가져오기")
        return User.fixture()
    }
}