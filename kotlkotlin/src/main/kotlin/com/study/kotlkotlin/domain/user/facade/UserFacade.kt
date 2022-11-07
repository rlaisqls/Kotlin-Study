package com.study.kotlkotlin.domain.user.facade

import com.study.kotlkotlin.domain.user.domain.User
import com.study.kotlkotlin.domain.user.domain.repository.UserRepository
import com.study.kotlkotlin.domain.user.error.PasswordMismatchException
import com.study.kotlkotlin.domain.user.error.UserAlreadyExistException
import com.study.kotlkotlin.domain.user.error.UserNotFoundException
import com.study.kotlkotlin.global.security.auth.AuthDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class UserFacade(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun getCurrentUser(): User {
        val authDetails = SecurityContextHolder.getContext().authentication.principal as AuthDetails
        return authDetails.user
    }

    fun findById(userId: Long) : User {
        return userRepository.findById(userId)
            .orElseThrow { UserNotFoundException.EXCEPTION }
    }

    fun findByUsername(username: String) : User {
        return userRepository.findByUsername(username) ?: throw UserNotFoundException.EXCEPTION
    }

    fun checkUsernameExist(username: String) {
        userRepository.findByUsername(username)?.let { throw UserAlreadyExistException.EXCEPTION }
    }

    fun checkPassword(user:User, password: String) {
        if (!passwordEncoder.matches(password, user.password)){
            throw PasswordMismatchException.EXCEPTION
        }
    }

}