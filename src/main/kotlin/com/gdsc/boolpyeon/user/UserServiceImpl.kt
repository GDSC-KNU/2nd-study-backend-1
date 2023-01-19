package com.gdsc.boolpyeon.user

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {
    override fun getUser(userId: Long): User {
        return userRepository.findByIdOrNull(userId)
            ?: throw IllegalArgumentException("존재하지 않는 User 입니다.")
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