package com.gdsc.boolpyeon.ConvenienceStore

import org.springframework.data.jpa.repository.JpaRepository

interface ConvenienceStoreRepository : JpaRepository<ConvenienceStore, Long?> {
    fun findByBranchName(branch_name: String): ConvenienceStore?
    // TODO: 필요한 것 추가
}