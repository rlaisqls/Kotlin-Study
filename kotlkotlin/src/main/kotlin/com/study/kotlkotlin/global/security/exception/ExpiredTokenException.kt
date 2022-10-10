package com.study.kotlkotlin.global.security.exception

import com.study.kotlkotlin.domain.user.error.UserErrorCode
import com.study.kotlkotlin.global.error.exception.BusinessException


class ExpiredTokenException private constructor() : BusinessException(SecurityErrorCode.EXPIRED_TOKEN) {

    companion object {
        @JvmField
        val EXCEPTION = ExpiredTokenException()
    }
}