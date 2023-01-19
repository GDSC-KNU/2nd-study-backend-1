package com.gdsc.boolpyeon.user.controller

import com.gdsc.boolpyeon.user.domain.User
import com.gdsc.boolpyeon.user.domain.dto.request.UserCreateRequest
import com.gdsc.boolpyeon.user.domain.dto.request.UserModifyRequest
import com.gdsc.boolpyeon.user.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService,
) {

    @GetMapping("/user/{id}")
    fun getUser(@PathVariable("id") userId: Int): User {
        return userService.getUser(userId)
    }

    @PostMapping("/user")
    fun createUser(@RequestBody request: UserCreateRequest) {
        userService.createUser(request)
    }

    @PutMapping("/user/{id}")
    fun modifyUser(@RequestBody request: UserModifyRequest) {
        userService.modifyUser(request)
    }

    @DeleteMapping("/user/{id}")
    fun deleteUser(@PathVariable("id") userId: Int) {
        userService.deleteUser(userId)
    }

    @GetMapping("/user/{id}/like-stores")
    fun getLikeStores(@PathVariable("id") userId: Int) {
        userService.getLikeStores(userId)
    }

    @GetMapping("/user/{id}/like-items")
    fun getLikeItems(@PathVariable("id") userId: Int) {
        userService.getLikeItems(userId)
    }


}