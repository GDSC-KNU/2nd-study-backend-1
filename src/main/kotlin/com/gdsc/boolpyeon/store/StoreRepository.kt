package com.gdsc.boolpyeon.store

import com.gdsc.boolpyeon.store.querydsl.StoreRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<Store, Long?>, StoreRepositoryCustom {
}