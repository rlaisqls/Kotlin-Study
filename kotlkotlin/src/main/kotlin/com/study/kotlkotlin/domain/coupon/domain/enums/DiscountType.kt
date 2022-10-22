package com.study.kotlkotlin.domain.coupon.domain.enums

enum class DiscountType(
    private val value: String
) {
    FIXED(Values.FIXED),
    RATE(Values.RATE);

    class Values {
        companion object {
            const val FIXED = "FIXED"
            const val RATE = "RATE"
        }
    }
}