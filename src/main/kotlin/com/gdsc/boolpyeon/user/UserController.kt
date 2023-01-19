package com.gdsc.boolpyeon.user

import com.gdsc.boolpyeon.user.dto.request.UserCreateRequest
import com.gdsc.boolpyeon.user.dto.request.UserModifyRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService,
) {

    @GetMapping("/user/{id}")
    fun getUser(@PathVariable("id") userId: Long): User {
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
    fun deleteUser(@PathVariable("id") userId: Long) {
        userService.deleteUser(userId)
    }

    @GetMapping("/user/{id}/favorite-stores")
    fun getFavoriteStores(@PathVariable("id") userId: Long) {
        userService.getFavoriteStores(userId)
    }

    @GetMapping("/user/{id}/like-items")
    fun getLikeItems(@PathVariable("id") userId: Long) {
        userService.getLikeItems(userId)
    }


}