package com.gdsc.boolpyeon.user.service

import com.gdsc.boolpyeon.like_item.LikeItem
import com.gdsc.boolpyeon.like_store.LikeStore
import com.gdsc.boolpyeon.user.domain.User
import com.gdsc.boolpyeon.user.domain.dto.request.UserCreateRequest
import com.gdsc.boolpyeon.user.domain.dto.request.UserModifyRequest

interface UserService {

    fun getUser(userId: Int): User

    fun createUser(request: UserCreateRequest): Int

    fun modifyUser(request: UserModifyRequest)

    fun deleteUser(userId: Int)

    fun getLikeStores(userId: Int): List<LikeStore>

    fun getLikeItems(userId: Int): List<LikeItem>
}