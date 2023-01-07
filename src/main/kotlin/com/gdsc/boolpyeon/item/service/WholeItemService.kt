package com.gdsc.boolpyeon.item.service

import com.gdsc.boolpyeon.item.entity.WholeItem

interface WholeItemService {
    fun getAllItems(): MutableList<WholeItem>
    fun getBrandItems(): MutableList<WholeItem>
}