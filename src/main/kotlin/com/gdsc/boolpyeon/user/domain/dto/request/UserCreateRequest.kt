package com.gdsc.boolpyeon.user.domain.dto.request

data class UserCreateRequest(
    val name: String,
    val mail: String,
    val phoneNumber: String,
)
