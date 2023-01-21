package com.gdsc.boolpyeon.item.service

import com.gdsc.boolpyeon.item.dto.DiscountItemDto

interface DiscountItemService {
    fun getAllItems(): List<DiscountItemDto>
    fun getBrandItems(brand: String): List<DiscountItemDto>
    fun getItem(itemId: Long): DiscountItemDto
    fun getBrandItemDetail(brand: String, itemId: Long): DiscountItemDto
}