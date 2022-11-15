package com.study.kotlkotlin.domain.user.presentation.dto.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

data class SignInRequest (

    @field:Length(min = 1, max = 15)
    @field:NotBlank
    val username: String,

    @field:Length(min = 1, max = 30)
    @field:NotBlank
    val password: String
)