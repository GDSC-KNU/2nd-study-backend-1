package com.gdsc.boolpyeon.store

import com.gdsc.boolpyeon.store.dto.StoreDto
import org.springframework.transaction.annotation.Transactional

class StoreServiceImpl : StoreService {

    @Transactional(readOnly = true)
    override fun getStore(storeId: Long): StoreDto {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getStores(): List<StoreDto> {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getNearStores(latitude: Double, longitude: Double): List<StoreDto> {
        TODO("Not yet implemented")
    }
}