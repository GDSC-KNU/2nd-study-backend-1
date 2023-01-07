package com.gdsc.boolpyeon.ConvenienceStore

import javax.persistence.*

@Entity
@Table(name = "conv_store")
class ConvenienceStore(
    branch_name: String,
    brand_name: String,
    latitude: Float,
    longitude: Float,
    favorite_count: Int
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    val branch_name: String = branch_name

    val brand_name: String = brand_name

    val latitude: Float = latitude

    val longitude: Float = longitude

    var favorite_count: Int = favorite_count

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ConvenienceStore

        if (id != other.id)
            return false
        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "User(id=$id, branch_name='$branch_name', brand_name='$brand_name', latitude='$latitude', longitude='$longitude')"
    }
}