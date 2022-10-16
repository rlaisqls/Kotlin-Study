package com.study.kotlkotlin.domain.auth.error

import com.study.kotlkotlin.global.error.exception.BusinessException

class RefreshTokenNotFoundException private constructor(): BusinessException(AuthErrorCode.REFRESH_TOKEN_NOT_FOUND){

    companion object {
        @JvmField
        val EXCEPTION = RefreshTokenNotFoundException()
    }
}