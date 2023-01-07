package com.gdsc.boolpyeon.item.service

import com.gdsc.boolpyeon.item.entity.WholeItem
import com.gdsc.boolpyeon.item.repository.WholeItemRepository

class WholeItemServiceImpl(private val wholeItemRepository: WholeItemRepository) : WholeItemService {
    override fun getAllItems() = wholeItemRepository.findAll()
    override fun getBrandItems(): MutableList<WholeItem> {
        TODO("Not yet implemented")
    }
}