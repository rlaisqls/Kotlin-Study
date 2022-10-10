package com.study.kotlkotlin.global.security.jwt

import com.study.kotlkotlin.domain.auth.domain.RefreshToken
import com.study.kotlkotlin.domain.auth.domain.repository.RefreshTokenRepository
import com.study.kotlkotlin.global.security.auth.AuthDetailsService
import com.study.kotlkotlin.global.security.exception.ExpiredTokenException
import com.study.kotlkotlin.global.security.exception.InvalidTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.Authentication
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest


@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val authDetailsService: AuthDetailsService
) {

    fun createAccessToken(email: String): String {
        return createToken(email, "access", jwtProperties.accessExp)
    }

    fun createRefreshToken(username: String): String {
        val token = createToken(username, "refresh", jwtProperties.refreshExp)
        refreshTokenRepository.save(
            RefreshToken(
                username = username,
                token = token,
                expiration = jwtProperties.refreshExp * 1000
            )
        )
        return token
    }

    private fun createToken(username: String, typ: String, exp: Long): String {
        return Jwts.builder()
            .setSubject(username)
            .claim("typ", typ)
            .signWith(Keys.hmacShaKeyFor(jwtProperties.secretKey.toByteArray()), SignatureAlgorithm.HS256)
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .setIssuedAt(Date())
            .compact()
    }

    fun getAuthentication(token: String): Authentication {

        val claims = getClaims(token)
        if (claims["typ"] == "access") {
            throw InvalidTokenException.EXCEPTION
        }
        val details = authDetailsService.loadUserByUsername(getUsername(claims))

        return UsernamePasswordAuthenticationToken(details, "", details.authorities)
    }

    private fun getUsername(claims: Claims): String {
        return claims.subject
    }

    private fun getClaims(token: String): Claims {
        return try {
            Jwts
                .parser()
                .setSigningKey(jwtProperties.secretKey)
                .parseClaimsJws(token)
                .body
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