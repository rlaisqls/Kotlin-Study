package com.study.kotlkotlin.domain.user.error

import com.study.kotlkotlin.global.error.exception.BusinessException

class UserAlreadyExistException private constructor(): BusinessException(UserErrorCode.USER_ALREADY_EXIST){

    companion object {
        @JvmField
        val EXCEPTION = UserAlreadyExistException()
    }
}