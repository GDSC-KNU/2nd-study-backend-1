package com.gdsc.boolpyeon.item.entity

import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "whole_item")
class WholeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    val id: Long? = null

    val item_name: String? = null
    val brand_name: String? = null
    val category: String? = null
    val price: Int? = null
    val image: String? = null
    val stock: Int? = null
    val release_date: LocalDateTime? = null
    val like_count: Int? = null
    val sales: Int? = null
}