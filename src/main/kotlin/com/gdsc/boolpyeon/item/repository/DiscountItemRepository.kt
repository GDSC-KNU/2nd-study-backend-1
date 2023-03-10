package com.gdsc.boolpyeon.item.repository

import com.gdsc.boolpyeon.item.entity.DiscountItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.ListResourceBundle

@Repository
interface DiscountItemRepository : JpaRepository<DiscountItem, Long> {
    fun findAllByBrandName(brand: String): List<DiscountItem>?

    fun findByItemName(itemName: String): DiscountItem?
}