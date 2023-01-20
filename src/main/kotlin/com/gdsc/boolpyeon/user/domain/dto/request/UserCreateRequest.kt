package com.gdsc.boolpyeon.user.domain.dto.request

data class UserCreateRequest(
    val name: String,
    val mail: String,
    val phoneNumber: String,
) {

    companion object {
        fun fixture(): UserCreateRequest {
            return UserCreateRequest(
                name = "김삿갓",
                mail = "gsg@kim.satgat",
                phoneNumber = "01087654321",
            )
        }
    }
}
