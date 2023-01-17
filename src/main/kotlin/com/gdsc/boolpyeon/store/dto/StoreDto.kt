package com.gdsc.boolpyeon.store.dto

import com.gdsc.boolpyeon.store.Store
import org.locationtech.jts.geom.Point

data class StoreDto(
    val id: Long,
    val branch_name: String,
    val brand_name: String,
    val location: Point,
    var like_count: Int? = null
) {
    constructor(store: Store) : this(
        id = store.id!!,
        branch_name = store.branch_name!!,
        brand_name = store.brand_name!!,
        location = store.location!!,
        like_count = store.like_count
    )
}