package com.study.kotlkotlin.global.error.exception

open class BusinessException(
    val errorProperty: ErrorProperty
): RuntimeException() {
}