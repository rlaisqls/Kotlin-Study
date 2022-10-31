package com.study.kotlkotlin.domain.user.service

import com.study.kotlkotlin.domain.auth.presentation.dto.response.TokenResponse
import com.study.kotlkotlin.domain.user.domain.User
import com.study.kotlkotlin.domain.user.domain.enums.Authority
import com.study.kotlkotlin.domain.user.domain.repository.UserRepository
import com.study.kotlkotlin.domain.user.facade.UserFacade
import com.study.kotlkotlin.domain.user.presentation.dto.request.SignUpRequest
import com.study.kotlkotlin.global.security.jwt.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class SignUpService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val userFacade: UserFacade,
    private val jwtTokenProvider: JwtTokenProvider
) {
    @Transactional
    fun execute(request: SignUpRequest): TokenResponse {

        userFacade.checkUsernameExist(request.username)

        val user = userRepository.save(
            User(
                username = request.username,
                password = passwordEncoder.encode(request.password),
                authority = Authority.USER
            )
        )

        return jwtTokenProvider.generateTokens(user.username, user.authority)
    }
}