package com.gdsc.boolpyeon.item.repository

import com.gdsc.boolpyeon.item.entity.WholeItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WholeItemRepository : JpaRepository<WholeItem, Long> {
}