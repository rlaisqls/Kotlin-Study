package com.study.kotlkotlin.domain.coupon.error

import com.study.kotlkotlin.global.error.exception.BusinessException

class ExpiredCouponException private constructor(): BusinessException(CouponErrorCode.EXPIRED_COUPON) {

    companion object {
        @JvmField
        val EXCEPTION = ExpiredCouponException()
    }
}