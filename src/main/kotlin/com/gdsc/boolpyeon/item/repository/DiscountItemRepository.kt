package com.gdsc.boolpyeon.item.repository

import com.gdsc.boolpyeon.item.entity.DiscountItem
import com.gdsc.boolpyeon.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DiscountItemRepository : JpaRepository<DiscountItem, Long> {
    fun findallbyBrand_name(brand: String): List<DiscountItem>?

    fun findByItem_name(itemName: String): DiscountItem?
}