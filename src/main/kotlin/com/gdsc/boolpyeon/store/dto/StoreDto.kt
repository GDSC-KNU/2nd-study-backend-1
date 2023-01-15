package com.gdsc.boolpyeon.store.dto

import com.gdsc.boolpyeon.store.Store

data class StoreDto(
    val id: Long,
    val branch_name: String,
    val brand_name: String,
    val latitude: Double,
    val longitude: Double,
    var like_count: Int? = null
) {
    constructor(store: Store) : this(
        id = store.id!!,
        branch_name = store.branch_name!!,
        brand_name = store.brand_name!!,
        latitude = store.latitude!!,
        longitude = store.longitude!!,
        like_count = store.like_count
    )
}