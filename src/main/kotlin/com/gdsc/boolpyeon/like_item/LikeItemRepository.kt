package com.gdsc.boolpyeon.like_item

import com.gdsc.boolpyeon.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface LikeItemRepository : JpaRepository<LikeItem, Int> {
    fun findAllByUser(user: User): List<LikeItem>
}