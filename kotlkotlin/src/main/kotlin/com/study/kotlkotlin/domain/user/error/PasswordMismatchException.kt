package com.study.kotlkotlin.domain.user.error

import com.study.kotlkotlin.global.error.exception.BusinessException

class PasswordMismatchException private constructor(): BusinessException(UserErrorCode.PASSWORD_MISMATCH){

    companion object {
        @JvmField
        val EXCEPTION = PasswordMismatchException()
    }
}