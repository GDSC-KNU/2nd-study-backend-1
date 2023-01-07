package com.gdsc.boolpyeon.user

import com.gdsc.boolpyeon.user.dto.request.UserLoginDto
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/signup")
    fun signup() {
        userService.signup()
    }

    @PostMapping("/login")
    fun login(@RequestBody userLoginDto: UserLoginDto) {
        userService.login()
    }

    @PostMapping("/logout")
    fun logout() {
        userService.logout()
    }

    @GetMapping("/user/{id}")
    fun getUser(@PathVariable("id") userId: Int): User {
        return userService.getUser(userId)
    }
}