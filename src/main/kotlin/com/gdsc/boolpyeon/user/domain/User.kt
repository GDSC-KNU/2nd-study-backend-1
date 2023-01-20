package com.gdsc.boolpyeon.user.domain

import com.gdsc.boolpyeon.like_store.LikeStore
import com.gdsc.boolpyeon.user.domain.dto.request.UserCreateRequest
import com.gdsc.boolpyeon.user.domain.dto.request.UserModifyRequest
import javax.persistence.*

@Entity
@Table(name = "users")
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
    var id: Int? = null

    @OneToMany(mappedBy = "user")
    var likeStore: MutableList<LikeStore> = mutableListOf()

    fun modify(request: UserModifyRequest) {
        // TODO( Front에서 null을 줄 수 있는가? )
        // TODO ( reflect property를 이용하면 코드를 더 줄일 수 있을지도 )
        request.name
            ?.let { name ->
                this.name = name
            }
        request.mail
            ?.let { mail ->
                this.mail = mail
            }
        request.phoneNumber
            ?.let { phoneNumber ->
                this.phoneNumber = phoneNumber
            }
    }

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