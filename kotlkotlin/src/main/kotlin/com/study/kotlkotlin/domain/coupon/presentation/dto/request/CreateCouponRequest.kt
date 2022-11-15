package com.study.kotlkotlin.domain.coupon.presentation.dto.request

import com.study.kotlkotlin.domain.coupon.domain.enums.DiscountType
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class CreateCouponRequest(

    @field:NotBlank
    @field:Length(min = 1, max = 30)
    val couponName: String,

    @field:NotNull
    val discountType: DiscountType,

    @field:NotNull
    val discountAmount: Int,

    @field:NotNull
    val validityPeriod: Int
)