package com.study.kotlkotlin.domain.user.error

import com.study.kotlkotlin.global.error.exception.ErrorProperty

enum class UserErrorCode (
    private val status: Int,
    private val message: String
) : ErrorProperty {

    PASSWORD_MISMATCH(401, "Password Mismatch"),
    USER_NOT_FOUND(404, "User Not Found"),
    USER_ALREADY_EXIST(409, "User Already Exist");

    override fun status(): Int = status
    override fun message(): String = message
}