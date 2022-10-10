package com.study.kotlkotlin.global.security.auth

import com.study.kotlkotlin.domain.user.domain.User
import com.study.kotlkotlin.domain.user.domain.repository.UserRepository
import com.study.kotlkotlin.domain.user.error.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepository.findByUsername(username) ?: throw UserNotFoundException.EXCEPTION
        return AuthDetails(user)
    }

}