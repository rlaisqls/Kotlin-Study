package com.study.kotlkotlin.domain.user.service

import com.study.kotlkotlin.domain.user.domain.repository.UserRepository
import com.study.kotlkotlin.domain.user.facade.UserFacade
import com.study.kotlkotlin.domain.user.presentation.dto.request.ChangePasswordRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class ChangePasswordService(
    private val userRepository: UserRepository,
    private val userFacade: UserFacade,
    private val passwordEncoder: PasswordEncoder
) {
    fun execute(request: ChangePasswordRequest) {

        val user = userFacade.getCurrentUser()
        userFacade.checkPassword(user, request.oldPassword)

        user.changePassword(passwordEncoder.encode(request.newPassword))
        userRepository.save(user)
    }
}