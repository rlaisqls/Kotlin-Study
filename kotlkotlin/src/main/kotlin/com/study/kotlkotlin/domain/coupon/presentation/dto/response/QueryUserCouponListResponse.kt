package com.study.kotlkotlin.domain.coupon.presentation.dto.response

import com.study.kotlkotlin.domain.coupon.domain.Coupon
import com.study.kotlkotlin.domain.coupon.domain.UserCoupon
import com.study.kotlkotlin.domain.coupon.domain.enums.DiscountType
import java.time.LocalDateTime

data class QueryUserCouponListResponse (
    val userCouponList: List<UserCouponResponse>
) {
    data class UserCouponResponse(
        val couponId: Long,
        val couponName: String,
        val discountAmount: String,
        val expirationDateTime: LocalDateTime
    ) {
        constructor(userCoupon: UserCoupon): this(
            couponId = userCoupon.coupon.id,
            couponName = userCoupon.coupon.name,
            discountAmount = userCoupon.coupon.discountAmount,
            expirationDateTime = userCoupon.expirationDateTime
        )
    }
}