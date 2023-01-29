package com.gdsc.boolpyeon.item.service

import com.gdsc.boolpyeon.item.dto.DiscountItemDto
import com.gdsc.boolpyeon.item.repository.DiscountItemRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DiscountItemServiceImpl(private val discountItemRepository: DiscountItemRepository) : DiscountItemService {
    override fun getAllItems(): List<DiscountItemDto> {
        return discountItemRepository.findAll().map { DiscountItemDto(it) }
    }

    @Transactional(readOnly = true)
    override fun searchItems(brand: String, category: String, event_type: String): List<DiscountItemDto> {
        val items = discountItemRepository.findAll()
        if (brand.isEmpty()) {
            if (category.isEmpty() && event_type.isNotEmpty()) {
                return items.filter { it.event == event_type }.map { DiscountItemDto(it) }
            } else if (category.isNotEmpty() && event_type.isEmpty()) {
                return items.filter { it.category == category }.map { DiscountItemDto(it) }
            } else if (category.isNotEmpty() && event_type.isNotEmpty()){
                return items.filter { it.event == event_type && it.category == category }.map { DiscountItemDto(it) }
            }
        }
        else if (brand.isNotEmpty()){
            if (category.isEmpty() && event_type.isNotEmpty()) {
                return items.filter { it.brandName == brand && it.event == event_type }.map { DiscountItemDto(it) }
            } else if (category.isNotEmpty() && event_type.isEmpty()) {
                return items.filter { it.brandName == brand && it.category == category }.map { DiscountItemDto(it) }
            } else if (category.isEmpty() && event_type.isEmpty()) {
                return items.filter { it.brandName == brand && it.event == event_type && it.category == category }.map { DiscountItemDto(it) }
            }
        }
        return items.map { DiscountItemDto(it) }
    }

    @Transactional(readOnly = true)
    override fun getBrandItems(brand: String): List<DiscountItemDto> {
        val item = discountItemRepository.findAllByBrandName(brand) ?: throw IllegalArgumentException("brand = $brand: 해당 브랜드의 상품이 존재하지 않습니다.")
        return item.map { DiscountItemDto(it) }
    }
    @Transactional(readOnly = true)
    override fun getItem(itemName: String): DiscountItemDto {
        val item =
            discountItemRepository.findByItemName(itemName) ?: throw IllegalArgumentException("name = $itemName: 해당 상품이 존재하지 않습니다.")
        return DiscountItemDto(item)
    }

    @Transactional(readOnly = true)
    override fun getBrandItemDetail(brand: String, itemId: Long): DiscountItemDto {
        val item =
            discountItemRepository.findByIdOrNull(itemId) ?: throw IllegalArgumentException("id = $itemId: 해당 상품이 존재하지 않습니다.")
        return DiscountItemDto(item)
    }

}