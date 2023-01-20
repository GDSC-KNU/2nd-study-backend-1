package com.gdsc.boolpyeon.user.service

import com.gdsc.boolpyeon.like_item.LikeItem
import com.gdsc.boolpyeon.like_item.LikeItemRepository
import com.gdsc.boolpyeon.like_store.LikeStore
import com.gdsc.boolpyeon.like_store.LikeStoreRepository
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
    private val likeStoreRepository: LikeStoreRepository,
    private val likeItemRepository: LikeItemRepository,
) : UserService {

    @Transactional(readOnly = true)
    override fun getUser(userId: Int): User {
        return userRepository.findByIdOrNull(userId)
            ?: throw IllegalArgumentException("존재하지 않는 User 입니다.")
    }

    @Transactional
    override fun createUser(request: UserCreateRequest): Int? {
        var user = User.fromRequest(request)
        user = userRepository.save(user)
        return user.id
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

    @Transactional(readOnly = true)
    override fun getLikeStores(userId: Int): List<LikeStore> {
        val user = this.getUser(userId)
        return likeStoreRepository.findAllByUser(user)
    }

    @Transactional(readOnly = true)
    override fun getLikeItems(userId: Int): List<LikeItem> {
        val user = this.getUser(userId)
        return likeItemRepository.findAllByUser(user)
    }
}