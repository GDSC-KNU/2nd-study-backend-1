package com.gdsc.boolpyeon.store.dto

data class StoreDto(
    val id: Long,
    val branch_name: String,
    val brand_name: String,
    val latitude: Double,
    val longitude: Double,
    var like_count: Int
)