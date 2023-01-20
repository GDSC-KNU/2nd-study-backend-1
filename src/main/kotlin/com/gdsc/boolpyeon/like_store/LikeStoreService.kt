package com.gdsc.boolpyeon.like_store

import com.gdsc.boolpyeon.store.Store
import com.gdsc.boolpyeon.user.domain.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LikeStoreService(
    private val likeStoreRepository: LikeStoreRepository,
) {
    @Transactional(readOnly = true)
    fun getLikeStores(user: User): List<LikeStore> {
        return likeStoreRepository.findAllByUser(user)
    }

    @Transactional
    fun addLikeStore(user: User, store: Store) {
        this.getLikeStores(user)
            .firstOrNull {
                it.store == store
            }?.let {
                throw IllegalArgumentException("이미 좋아하는 편의점입니다.")
            } ?: run {
            val likeStore = LikeStore(
                user,
                store,
            )
            likeStoreRepository.save(likeStore)
        }
    }
}