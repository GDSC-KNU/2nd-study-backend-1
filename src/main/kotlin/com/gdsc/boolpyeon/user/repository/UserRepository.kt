package com.gdsc.boolpyeon.user.repository

import com.gdsc.boolpyeon.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
}