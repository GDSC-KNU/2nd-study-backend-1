package com.gdsc.boolpyeon.like_item

import com.gdsc.boolpyeon.item.entity.DiscountItem
import com.gdsc.boolpyeon.user.domain.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LikeItemService(
    private val likeItemRepository: LikeItemRepository,
) {

    @Transactional(readOnly = true)
    fun getLikeItems(user: User): List<LikeItem> {
        return likeItemRepository.findAllByUser(user)
    }

    @Transactional
    fun addLikeItem(user: User, item: DiscountItem) {
        this.getLikeItems(user)
            .firstOrNull {
                it.item == item
            }?.let {
                throw IllegalArgumentException("이미 좋아하는 상품입니다.")
            } ?: run {
            val likeItem = LikeItem(
                user,
                item,
            )
            likeItemRepository.save(likeItem)
        }
    }
}