package com.gdsc.boolpyeon.item.repository

import com.gdsc.boolpyeon.item.entity.DiscountItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DiscountItemRepository : JpaRepository<DiscountItem, Long> {
}