package com.study.kotlkotlin.domain.user.domain.repository

import com.study.kotlkotlin.domain.user.domain.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
}