package com.study.kotlkotlin.domain.auth.domain.repository

import com.study.kotlkotlin.domain.auth.domain.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository: CrudRepository<RefreshToken, String> {
    fun findByToken(token: String): RefreshToken?
}