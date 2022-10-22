package com.study.kotlkotlin.domain.coupon.error

import com.study.kotlkotlin.global.error.exception.ErrorProperty

enum class CouponErrorCode (
    private val status: Int,
    private val message: String
): ErrorProperty {

    COUPON_NOT_FOUND(404, "Coupon Not Found"),
    EXPIRED_COUPON(409, "Expired Coupon");

    override fun message(): String = message
    override fun status(): Int = status
}