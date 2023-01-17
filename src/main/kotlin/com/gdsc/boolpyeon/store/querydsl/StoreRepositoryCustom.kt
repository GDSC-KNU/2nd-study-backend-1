package com.gdsc.boolpyeon.store.querydsl

import com.gdsc.boolpyeon.store.Store

interface StoreRepositoryCustom {
    fun findNearStores(latitude: Double, longitude: Double): List<Store>
}