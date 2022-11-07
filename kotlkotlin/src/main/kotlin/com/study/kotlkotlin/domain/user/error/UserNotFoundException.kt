package com.study.kotlkotlin.domain.user.error

import com.study.kotlkotlin.global.error.exception.BusinessException

class UserNotFoundException private constructor(): BusinessException(UserErrorCode.USER_NOT_FOUND){

    companion object {
        @JvmField
        val EXCEPTION = UserNotFoundException()
    }
}