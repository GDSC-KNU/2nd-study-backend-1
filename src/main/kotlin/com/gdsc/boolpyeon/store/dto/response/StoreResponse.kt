package com.gdsc.boolpyeon.store.dto.response

import com.gdsc.boolpyeon.store.dto.StoreDto

data class StoreResponse(
    val id: Long,
    val branch_name: String,
    val brand_name: String,
    val latitude: Double,
    val longitude: Double,
    var like_count: Int
) {
    companion object {
        fun of(
            id: Long,
            branch_name: String,
            brand_name: String,
            latitude: Double,
            longitude: Double,
            like_count: Int
        ): StoreResponse {
            return StoreResponse(id, branch_name, brand_name, latitude, longitude, like_count)
        }

        fun from(dto: StoreDto): StoreResponse {
            return StoreResponse(
                dto.id,
                dto.branch_name,
                dto.brand_name,
                dto.latitude,
                dto.longitude,
                dto.like_count
            )
        }
    }
}
