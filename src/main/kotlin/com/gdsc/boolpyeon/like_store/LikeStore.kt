package com.gdsc.boolpyeon.like_store

import com.gdsc.boolpyeon.store.Store
import com.gdsc.boolpyeon.user.domain.User
import javax.persistence.*

@Entity
@Table(name = "LikeStore")
class LikeStore(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    val store: Store,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
) {
}