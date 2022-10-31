package com.study.kotlkotlin.domain.user.presentation.dto.request

import org.hibernate.validator.constraints.Length

data class SignInRequest (

    @field:Length(min = 1, max = 15, message = "username은 15자 이하여야 합니다.")
    val username: String,

    @field:Length(min = 1, max = 30, message = "password는 8자 이상, 30자 이하여야 합니다.")
    val password: String
)