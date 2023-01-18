package com.gdsc.boolpyeon.item.service

import com.gdsc.boolpyeon.item.dto.DiscountItemDto
import com.gdsc.boolpyeon.item.repository.DiscountItemRepository

class DiscountItemServiceImpl(private val discountItemRepository: DiscountItemRepository) : DiscountItemService {
    override fun getAllItems(): List<DiscountItemDto> {
        TODO("Not yet implemented")
    }
    override fun getBrandItems(): List<DiscountItemDto> {
        TODO("Not yet implemented")
    }

    override fun getItem(): List<DiscountItemDto> {
        TODO("Not yet implemented")
    }

    override fun getBrandItemDetail(): List<DiscountItemDto> {
        TODO("Not yet implemented")
    }

}