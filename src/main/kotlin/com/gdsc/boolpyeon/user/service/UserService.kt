package com.gdsc.boolpyeon.user.service

import com.gdsc.boolpyeon.user.domain.User
import com.gdsc.boolpyeon.user.domain.dto.request.UserCreateRequest
import com.gdsc.boolpyeon.user.domain.dto.request.UserModifyRequest

interface UserService {

    fun getUser(userId: Long): User

    fun createUser(request: UserCreateRequest)

    fun modifyUser(request: UserModifyRequest)

    fun deleteUser(userId: Long)

    fun getFavoriteStores(userId: Long)

    fun getLikeItems(userId: Long)
}