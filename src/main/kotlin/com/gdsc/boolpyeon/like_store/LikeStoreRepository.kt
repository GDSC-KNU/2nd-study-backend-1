package com.gdsc.boolpyeon.like_store

import com.gdsc.boolpyeon.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface LikeStoreRepository : JpaRepository<LikeStore, Int> {
    fun findAllByUser(user: User): List<LikeStore>
}