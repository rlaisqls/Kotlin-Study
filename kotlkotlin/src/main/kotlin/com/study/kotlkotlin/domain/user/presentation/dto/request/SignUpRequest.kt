package com.study.kotlkotlin.domain.user.presentation.dto.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern

data class SignUpRequest(

    @Length(min = 1, max = 15, message = "username은 15자 이하여야 합니다.")
    val username: String,

    @Email(message = "email 형식이 올바르지 않습니다")
    val email: String,

    @Pattern(
        regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;" + "<=>?@＼^_`{|}~]{8,30}$",
        message = "password는 숫자, 특수문자가 포함되어야 합니다."
    )
    val password: String

)