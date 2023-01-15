package com.gdsc.boolpyeon.store

import com.gdsc.boolpyeon.store.dto.StoreDto

interface StoreService {
    fun getStore(storeId: Long): StoreDto
    fun getStores(): List<StoreDto>
    fun getNearStores(latitude: Double, longitude: Double): List<StoreDto>
}