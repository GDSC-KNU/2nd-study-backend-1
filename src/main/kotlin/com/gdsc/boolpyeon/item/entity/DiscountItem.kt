package com.gdsc.boolpyeon.item.entity

import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "discount_item")
class DiscountItem {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    val id: Long? = null

    val brand_name: String? = null
    val category: String? = null
    val item_name: String? = null
    val price: Int? = null
    val discount: String? = null
    val event: String? = null
    val event_month: LocalDateTime? = null
    val image: String? = null
    val like_count: Int? = null

    override fun equals(other: Any?): Boolean {
        return if (other is DiscountItem) {
            other.brand_name == this.brand_name && other.item_name == this.item_name
        } else false
    }
}