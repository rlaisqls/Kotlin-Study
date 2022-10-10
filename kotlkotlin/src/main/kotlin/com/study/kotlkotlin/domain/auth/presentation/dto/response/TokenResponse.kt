package com.study.kotlkotlin.domain.auth.presentation.dto.response

import java.util.*

data class TokenResponse(
    val accessToken: String,
    val accessTokenExp: Date,
    val refreshToken: String
)