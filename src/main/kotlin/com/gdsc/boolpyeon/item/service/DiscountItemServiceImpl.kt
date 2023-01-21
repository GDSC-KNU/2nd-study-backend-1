package com.gdsc.boolpyeon.item.service

import com.gdsc.boolpyeon.item.dto.DiscountItemDto
import com.gdsc.boolpyeon.item.dto.response.DiscountItemResponseDto
import com.gdsc.boolpyeon.item.entity.DiscountItem
import com.gdsc.boolpyeon.item.repository.DiscountItemRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DiscountItemServiceImpl(private val discountItemRepository: DiscountItemRepository) : DiscountItemService {

    @Transactional(readOnly = true)
    override fun getAllItems(): List<DiscountItemDto> {
        return discountItemRepository.findAll().map { DiscountItemDto(it) }
    }

    @Transactional(readOnly = true)
    override fun getBrandItems(brand: String): List<DiscountItemDto> {
        TODO("Not yet implemented")
    }
    @Transactional(readOnly = true)
    override fun getItem(itemId: Long): DiscountItemDto {
        val item =
            discountItemRepository.findByIdOrNull(itemId) ?: throw IllegalArgumentException("id = $itemId: 해당 상품이 존재하지 않습니다.")
        return DiscountItemDto(item)
    }

    @Transactional(readOnly = true)
    override fun getBrandItemDetail(brand: String, itemId: Long): DiscountItemDto {
        TODO("Not yet implemented")
    }

}