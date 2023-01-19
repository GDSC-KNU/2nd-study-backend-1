package com.gdsc.boolpyeon.user

import com.gdsc.boolpyeon.user.dto.request.UserCreateRequest
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

    override fun createUser(request: UserCreateRequest) {
        val user = User.fromRequest(request)
        userRepository.save(user)
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