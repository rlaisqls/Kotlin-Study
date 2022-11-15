package com.study.kotlkotlin.domain.user.presentation.dto.request

import com.study.kotlkotlin.global.util.RegexpUtil
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class SignUpRequest(

    @field:Length
    @field:NotBlank
    val username: String,

    @field:Email
    @field:NotBlank
    val email: String,

    @field:Pattern(regexp = RegexpUtil.PASSWORD_PATTERN)
    @field:NotBlank
    val password: String

)