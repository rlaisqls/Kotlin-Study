package com.study.kotlkotlin.domain.auth.error

import com.study.kotlkotlin.global.error.exception.ErrorProperty

enum class AuthErrorCode(
    private val status: Int,
    private val message: String
): ErrorProperty {

    REFRESH_TOKEN_NOT_FOUND(401, "Refresh Token Not Found");

    override fun message(): String = message
    override fun status(): Int = status
}