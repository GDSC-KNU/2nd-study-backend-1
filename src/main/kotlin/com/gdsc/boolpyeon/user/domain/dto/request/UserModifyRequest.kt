package com.gdsc.boolpyeon.user.domain.dto.request

// TODO( Front에서 null을 줄 수 있는가? )
data class UserModifyRequest(
    val id: Int,
    val name: String?,
    val mail: String?,
    val phoneNumber: String?,
)
