package com.gdsc.boolpyeon.store

import com.gdsc.boolpyeon.store.dto.StoreDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StoreServiceImpl(
    private val storeRepository: StoreRepository
) : StoreService {

    @Transactional(readOnly = true)
    override fun getStore(storeId: Long): StoreDto {
        val store =
            storeRepository.findByIdOrNull(storeId) ?: throw IllegalArgumentException("id = $storeId: 편의점이 존재하지 않습니다.")
        return StoreDto(store)
    }

    @Transactional(readOnly = true)
    override fun getStores(): List<StoreDto> {
        return storeRepository.findAll().map { StoreDto(it) }
    }

    @Transactional(readOnly = true)
    override fun getNearStores(latitude: Double, longitude: Double): MutableList<StoreDto> {
        TODO("Not yet implemented")
    }
}