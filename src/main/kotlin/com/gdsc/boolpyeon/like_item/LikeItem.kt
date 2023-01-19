package com.gdsc.boolpyeon.like_item

import com.gdsc.boolpyeon.item.entity.DiscountItem
import com.gdsc.boolpyeon.user.domain.User
import javax.persistence.*

@Entity
@Table(name = "LikeItem")
class LikeItem(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    val item: DiscountItem,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
)