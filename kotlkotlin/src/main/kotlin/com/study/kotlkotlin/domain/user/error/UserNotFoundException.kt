package com.study.kotlkotlin.domain.user.error

import com.study.kotlkotlin.global.error.exception.BusinessException

object UserNotFoundException: BusinessException(UserErrorCode.USER_NOT_FOUND)