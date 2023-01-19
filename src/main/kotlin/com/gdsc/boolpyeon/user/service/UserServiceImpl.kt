package com.gdsc.boolpyeon.user.service

import com.gdsc.boolpyeon.user.domain.User
import com.gdsc.boolpyeon.user.domain.dto.request.UserCreateRequest
import com.gdsc.boolpyeon.user.domain.dto.request.UserModifyRequest
import com.gdsc.boolpyeon.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {

    @Transactional(readOnly = true)
    override fun getUser(userId: Int): User {
        return userRepository.findByIdOrNull(userId)
            ?: throw IllegalArgumentException("존재하지 않는 User 입니다.")
    }

    @Transactional
    override fun createUser(request: UserCreateRequest) {
        val user = User.fromRequest(request)
        userRepository.save(user)
    }

    @Transactional
    override fun modifyUser(request: UserModifyRequest) {
        val user = this.getUser(request.id)
        user.modify(request)
    }

    @Transactional
    override fun deleteUser(userId: Int) {
        val user = this.getUser(userId)
        userRepository.delete(user)
    }

    override fun getFavoriteStores(userId: Int) {
        TODO("Not yet implemented")
    }

    override fun getLikeItems(userId: Int) {
        TODO("Not yet implemented")
    }
}