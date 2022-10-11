package com.study.kotlkotlin.domain.coupon.error

import com.study.kotlkotlin.global.error.exception.BusinessException

class CouponNotFoundException private constructor(): BusinessException(CouponErrorCode.COUPON_NOT_FOUND){

    companion object {
        @JvmField
        val EXCEPTION = CouponNotFoundException()
    }
}