package com.gdsc.boolpyeon.user.service

import com.gdsc.boolpyeon.user.domain.User
import com.gdsc.boolpyeon.user.domain.dto.request.UserCreateRequest
import com.gdsc.boolpyeon.user.domain.dto.request.UserModifyRequest

interface UserService {

    fun getUser(userId: Int): User

    fun createUser(request: UserCreateRequest)

    fun modifyUser(request: UserModifyRequest)

    fun deleteUser(userId: Int)

    fun getFavoriteStores(userId: Int)

    fun getLikeItems(userId: Int)
}