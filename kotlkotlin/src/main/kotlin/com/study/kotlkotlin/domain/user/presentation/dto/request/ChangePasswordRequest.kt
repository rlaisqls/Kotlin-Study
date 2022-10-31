package com.study.kotlkotlin.domain.user.presentation.dto.request

import javax.validation.constraints.Pattern

data class ChangePasswordRequest(

    val oldPassword: String,

    @field:Pattern(
        regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;<=>?@＼^_`{|}~]{8,30}$",
        message = "password는 숫자, 특수문자가 포함되어야 합니다."
    )
    val newPassword: String
)