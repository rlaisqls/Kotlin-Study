package com.study.kotlkotlin.global.security.exception

import com.study.kotlkotlin.global.error.exception.BusinessException

class InvalidTokenException private constructor() : BusinessException(SecurityErrorCode.INVALID_TOKEN) {

    companion object {
        @JvmField
        val EXCEPTION = InvalidTokenException()
    }
}