package com.gdsc.boolpyeon.item.service

import com.gdsc.boolpyeon.item.dto.DiscountItemDto

interface DiscountItemService {
    fun getAllItems(): List<DiscountItemDto>
    fun searchItems(brand: String, category: String, event_type: String): List<DiscountItemDto>
    fun getBrandItems(brand: String): List<DiscountItemDto>
    fun getItem(itemName: String): DiscountItemDto
    fun getBrandItemDetail(brand: String, itemId: Long): DiscountItemDto
}