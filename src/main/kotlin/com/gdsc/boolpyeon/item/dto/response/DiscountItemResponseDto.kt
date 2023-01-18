package com.gdsc.boolpyeon.item.dto.response

import com.gdsc.boolpyeon.item.dto.DiscountItemDto
import java.time.LocalDateTime

data class DiscountItemResponseDto (
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
    companion object {
        fun of(
            id: Long,
            brand_name: String,
            category: String,
            item_name: String,
            price: Int,
            discount: String,
            event: String,
            event_month: LocalDateTime,
            image: String,
            like_count: Int?
        ) : DiscountItemResponseDto {
            return DiscountItemResponseDto(id, brand_name, category, item_name,
                price, discount, event, event_month, image, like_count)
        }
        fun from(dto: DiscountItemDto) : DiscountItemResponseDto {
            return DiscountItemResponseDto(
                dto.id,
                dto.brand_name,
                dto.category,
                dto.item_name,
                dto.price,
                dto.discount,
                dto.event,
                dto.event_month,
                dto.image,
                dto.like_count
            )
        }
    }
}