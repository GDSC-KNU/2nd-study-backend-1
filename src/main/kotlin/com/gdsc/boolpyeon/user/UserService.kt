package com.gdsc.boolpyeon.user

interface UserService {

    fun getUser(userId: Long): User

    fun createUser()

    fun modifyUser()

    fun deleteUser(userId: Long)

    fun getFavoriteStores(userId: Long)

    fun getLikeItems(userId: Long)
}