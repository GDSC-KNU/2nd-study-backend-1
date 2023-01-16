package com.gdsc.boolpyeon.store.dto.response

import com.gdsc.boolpyeon.store.dto.StoreDto
import org.locationtech.jts.geom.Point


data class StoreResponse(
    val id: Long?,
    val branch_name: String?,
    val brand_name: String?,
    val location: Point?,
    var like_count: Int?
) {
    companion object {
        fun of(
            id: Long,
            branch_name: String,
            brand_name: String,
            location: Point,
            like_count: Int
        ): StoreResponse {
            return StoreResponse(id, branch_name, brand_name, location, like_count)
        }

        fun from(dto: StoreDto): StoreResponse {
            return StoreResponse(
                dto.id,
                dto.branch_name,
                dto.brand_name,
                dto.location,
                dto.like_count
            )
        }
    }
}
