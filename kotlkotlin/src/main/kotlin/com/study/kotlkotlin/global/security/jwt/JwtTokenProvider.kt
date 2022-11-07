package com.study.kotlkotlin.global.security.jwt

import com.study.kotlkotlin.domain.auth.domain.RefreshToken
import com.study.kotlkotlin.domain.auth.domain.repository.RefreshTokenRepository
import com.study.kotlkotlin.domain.auth.presentation.dto.response.TokenResponse
import com.study.kotlkotlin.domain.user.domain.enums.Authority
import com.study.kotlkotlin.global.security.auth.AuthDetailsService
import com.study.kotlkotlin.global.security.exception.ExpiredTokenException
import com.study.kotlkotlin.global.security.exception.InvalidTokenException
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*
import javax.servlet.http.HttpServletRequest


@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val authDetailsService: AuthDetailsService
) {

    fun generateTokens(username: String, authority: Authority): TokenResponse {
        return TokenResponse(
            accessToken = createAccessToken(username, authority),
            accessTokenExp = LocalDateTime.now().plusSeconds(jwtProperties.accessExp),
            refreshToken = createRefreshToken(username, authority)
        )
    }

    fun createAccessToken(username: String, authority: Authority): String {
        return createToken(username, authority, "access", jwtProperties.accessExp)
    }

    fun createRefreshToken(username: String, authority: Authority): String {
        val token = createToken(username, authority, "refresh", jwtProperties.refreshExp)
        refreshTokenRepository.save(
            RefreshToken(
                username = username,
                token = token,
                authority = authority,
                expiration = jwtProperties.refreshExp * 1000
            )
        )
        return token
    }

    private fun createToken(username: String, authority: Authority, jwtType: String, exp: Long): String {
        return Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(jwtProperties.secretKey.toByteArray()), SignatureAlgorithm.HS256)
            .setSubject(username)
            .setHeaderParam(Header.JWT_TYPE, jwtType)
            .setId(username)
            .claim("authority", authority)
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .setIssuedAt(Date())
            .compact()
    }

    fun getAuthentication(token: String): Authentication {

        val claims = getClaims(token)
        if (claims.header[Header.JWT_TYPE] == "access") {
            throw InvalidTokenException.EXCEPTION
        }

        val details = authDetailsService.loadUserByUsername(claims.body.id)
        return UsernamePasswordAuthenticationToken(details, "", details.authorities)
    }

    private fun getClaims(token: String): Jws<Claims> {
        return try {
            Jwts
                .parser()
                .setSigningKey(jwtProperties.secretKey)
                .parseClaimsJws(token)
        } catch (e: Exception) {
            when (e) {
                is ExpiredTokenException -> throw ExpiredTokenException.EXCEPTION
                else -> throw InvalidTokenException.EXCEPTION
            }
        }
    }

    fun resolveToken(request: HttpServletRequest): String? {

        val bearerToken = request.getHeader(jwtProperties.header)

        if (bearerToken != null && (bearerToken.startsWith(jwtProperties.header))) {
            return bearerToken.substring(7)
        }
        return null
    }

}