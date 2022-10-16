package com.study.kotlkotlin.domain.auth.service

import com.study.kotlkotlin.domain.auth.domain.RefreshToken
import com.study.kotlkotlin.domain.auth.domain.repository.RefreshTokenRepository
import com.study.kotlkotlin.domain.auth.error.RefreshTokenNotFoundException
import com.study.kotlkotlin.domain.auth.presentation.dto.response.TokenResponse
import com.study.kotlkotlin.global.security.jwt.JwtProperties
import com.study.kotlkotlin.global.security.jwt.JwtTokenProvider
import org.springframework.stereotype.Service


@Service
class TokenRefreshService(
    private val jwtTokenProvider: JwtTokenProvider,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun execute(refreshToken: String): TokenResponse {

        val redisRefreshToken: RefreshToken = refreshTokenRepository.findByToken(refreshToken)
            ?: throw RefreshTokenNotFoundException.EXCEPTION

        val username = redisRefreshToken.username
        val authority = redisRefreshToken.authority

        val tokens = jwtTokenProvider.generateTokens(username, authority)

        redisRefreshToken.updateToken(tokens.refreshToken, jwtProperties.refreshExp)

        return tokens
    }
}