package com.gdsc.boolpyeon.item.dto

import java.time.LocalDateTime

data class WholeItemDto(
    val id: Long,
    val item_name: String,
    val brand_name: String,
    val category: String,
    val price: Int,
    val image: String,
    val stock: Int,
    val release_date: LocalDateTime,
    val like_count: Int,
    val sales: Int
)