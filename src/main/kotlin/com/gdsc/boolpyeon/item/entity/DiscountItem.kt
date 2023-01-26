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

    @Column(name = "brand_name")
    val brandName: String? = null
    val category: String? = null
    @Column(name = "item_name")
    val itemName: String? = null
    val price: Int? = null
    val discount: String? = null
    val event: String? = null
    @Column(name = "event_month")
    val eventMonth: LocalDateTime? = null
    val image: String? = null
    @Column(name = "like_count")
    val likeCount: Int? = null

    override fun equals(other: Any?): Boolean {
        return if (other is DiscountItem) {
            other.brandName == this.brandName && other.itemName == this.itemName
        } else false
    }
}