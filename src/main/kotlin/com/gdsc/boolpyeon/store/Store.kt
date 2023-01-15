package com.gdsc.boolpyeon.store

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Store {
    @Id
    @GeneratedValue
    var id: Long? = null
    
    val branch_name: String? = null
    val brand_name: String? = null
    val latitude: Double? = null
    val longitude: Double? = null
    var like_count: Int? = null
}