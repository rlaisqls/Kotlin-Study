package com.study.kotlkotlin.domain.user.presentation.dto.request

import com.study.kotlkotlin.global.util.RegexpUtil
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class ChangePasswordRequest(

    @field:NotBlank
    val oldPassword: String,

    @field:NotBlank
    @field:Pattern(regexp = RegexpUtil.PASSWORD_PATTERN)
    val newPassword: String
)