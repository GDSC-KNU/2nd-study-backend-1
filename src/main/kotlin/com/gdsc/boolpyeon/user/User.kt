package com.gdsc.boolpyeon.user

import com.gdsc.boolpyeon.user.dto.request.UserCreateRequest
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

        fun fromRequest(request: UserCreateRequest): User {
            return User(
                name = request.name,
                mail = request.mail,
                phoneNumber = request.phoneNumber,
            )
        }
    }

}