package com.gdsc.boolpyeon.item.service

import com.gdsc.boolpyeon.item.dto.DiscountItemDto

interface DiscountItemService {
    fun getAllItems(): List<DiscountItemDto>
    fun getBrandItems(): List<DiscountItemDto>
    fun getItem(): List<DiscountItemDto>
    fun getBrandItemDetail(): List<DiscountItemDto>
}