package com.study.kotlkotlin.domain.user.service

import com.study.kotlkotlin.domain.auth.presentation.dto.response.TokenResponse
import com.study.kotlkotlin.domain.user.facade.UserFacade
import com.study.kotlkotlin.domain.user.presentation.dto.request.SignInRequest
import com.study.kotlkotlin.global.security.jwt.JwtTokenProvider
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class SignInService(
    private val userFacade: UserFacade,
    private val jwtTokenProvider: JwtTokenProvider
) {
    @Transactional
    fun execute(request: SignInRequest): TokenResponse {

        val username = request.username
        val password = request.password

        val user = userFacade.findByUsername(username)

        userFacade.checkPassword(user, password)

        return jwtTokenProvider.generateTokens(username, user.authority)
    }
}