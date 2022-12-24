package com.study.kotlkotlin.domain.user.error

import com.study.kotlkotlin.global.error.exception.BusinessException

object PasswordMismatchException: BusinessException(UserErrorCode.PASSWORD_MISMATCH)