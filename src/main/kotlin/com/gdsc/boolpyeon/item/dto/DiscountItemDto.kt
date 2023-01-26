package com.gdsc.boolpyeon.item.dto

import com.gdsc.boolpyeon.item.entity.DiscountItem
import java.time.LocalDateTime

data class DiscountItemDto(
    val id: Long,
    val brand_name: String,
    val category: String,
    val item_name: String,
    val price: Int,
    val discount: String,
    val event: String,
    val event_month: LocalDateTime,
    val image: String,
    var like_count: Int? = null
) {
    constructor(discountItem: DiscountItem) : this(
        id = discountItem.id!!,
        brand_name = discountItem.brandName!!,
        category = discountItem.category!!,
        item_name = discountItem.itemName!!,
        price = discountItem.price!!,
        discount = discountItem.discount!!,
        event = discountItem.event!!,
        event_month = discountItem.eventMonth!!,
        image = discountItem.image!!,
        like_count = discountItem.likeCount!!
    )
}