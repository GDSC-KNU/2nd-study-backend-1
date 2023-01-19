package com.gdsc.boolpyeon.user

import com.gdsc.boolpyeon.user.dto.request.UserCreateRequest

interface UserService {

    fun getUser(userId: Long): User

    fun createUser(request: UserCreateRequest)

    fun modifyUser()

    fun deleteUser(userId: Long)

    fun getFavoriteStores(userId: Long)

    fun getLikeItems(userId: Long)
}