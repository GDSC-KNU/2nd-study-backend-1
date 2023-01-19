package com.gdsc.boolpyeon.user

import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    override fun getUser(userId: Long): User {
        TODO("실제 유저 정보 가져오기")
        return User.fixture()
    }

    override fun createUser() {
        TODO("Not yet implemented")
    }

    override fun modifyUser() {
        TODO("Not yet implemented")
    }

    override fun deleteUser(userId: Long) {
        TODO("Not yet implemented")
    }

    override fun getFavoriteStores(userId: Long) {
        TODO("Not yet implemented")
    }

    override fun getLikeItems(userId: Long) {
        TODO("Not yet implemented")
    }
}