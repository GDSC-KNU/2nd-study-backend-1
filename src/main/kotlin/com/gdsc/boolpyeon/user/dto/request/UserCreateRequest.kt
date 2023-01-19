package com.gdsc.boolpyeon.user.dto.request

data class UserCreateRequest(
    val name: String,
    val mail: String,
    val phoneNumber: String,
) {

}
