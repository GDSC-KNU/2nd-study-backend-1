package com.gdsc.boolpyeon.user

import javax.persistence.*

@Entity
class User(
    @Column
    var name: String,

    @Column
    var mail: String,

    @Column
    var phoneNumber: String,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어있을 수 없습니다.")
        }
    }

    companion object {
        fun fixture(): User {
            return User(
                name = "홍길동",
                mail = "abc@def.ghi",
                phoneNumber = "01012345678",
            )
        }
    }

}