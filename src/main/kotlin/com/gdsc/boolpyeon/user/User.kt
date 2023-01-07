package com.gdsc.boolpyeon.user

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(
    var name: String,

    val mail: String?,

    val phone_number: String?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {

    companion object {
        fun fixture(): User {
            return User(
                name = "홍길동",
                mail = null,
                phone_number = null,
            )
        }
    }

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어있을 수 없습니다.")
        }
    }
}