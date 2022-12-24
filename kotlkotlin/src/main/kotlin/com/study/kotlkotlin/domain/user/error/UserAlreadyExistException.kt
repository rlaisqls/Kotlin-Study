package com.study.kotlkotlin.domain.user.error

import com.study.kotlkotlin.global.error.exception.BusinessException

object UserAlreadyExistException: BusinessException(UserErrorCode.USER_ALREADY_EXIST)