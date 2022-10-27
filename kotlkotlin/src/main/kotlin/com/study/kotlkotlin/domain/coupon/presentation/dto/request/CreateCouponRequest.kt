package com.study.kotlkotlin.domain.coupon.presentation.dto.request

import com.study.kotlkotlin.domain.coupon.domain.enums.DiscountType
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class CreateCouponRequest(
    @field:NotBlank(message = "couponName는 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @field:Length(min = 1, max = 30, message = "couponName는 30자 이하여야 합니다.")
    val couponName: String,

    @NotNull(message = "discountType는 null을 허용하지 않습니다")
    val discountType: DiscountType,

    @NotNull(message = "discountAmount는 null을 허용하지 않습니다")
    val discountAmount: Int,

    @NotNull(message = "validityPeriod는 null을 허용하지 않습니다")
    val validityPeriod: Int
)