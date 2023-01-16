package com.gdsc.boolpyeon.store

import org.locationtech.jts.geom.Point
import javax.persistence.*

@Entity
@Table(name = "store")
class Store {
    @Id
    @GeneratedValue
    var id: Long? = null
    val branch_name: String? = null
    val brand_name: String? = null

    @Column(columnDefinition = "GEOMETRY")
    val location: Point? = null
    var like_count: Int? = null
}