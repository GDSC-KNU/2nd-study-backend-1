package com.gdsc.boolpyeon.store.querydsl

import com.gdsc.boolpyeon.store.QStore
import com.gdsc.boolpyeon.store.Store
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory

class StoreRepositoryCustomImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : StoreRepositoryCustom {

    override fun findNearStores(latitude: Double, longitude: Double): List<Store> {
        return jpaQueryFactory.selectFrom(QStore.store)
            .orderBy(
                Expressions.stringTemplate(
                    "ST_Distance_Sphere({0}, location)",
                    Expressions.stringTemplate(
                        "POINT({0}, {1})",
                        longitude, latitude
                    )
                ).asc()
            )
            .limit(10)
            .fetch()
    }
}