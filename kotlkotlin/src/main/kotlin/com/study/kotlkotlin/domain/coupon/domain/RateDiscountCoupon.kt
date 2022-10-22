package com.study.kotlkotlin.domain.coupon.domain

import com.study.kotlkotlin.domain.coupon.domain.enums.DiscountType
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.validation.constraints.Size

@DiscriminatorValue(DiscountType.Values.RATE)
@Entity
class RateDiscountCoupon(
    name: String,
    validityPeriod: Int,
    discountRate: Int
): Coupon(name, validityPeriod) {

    @Size(min = 0, max = 100)
    @Column(nullable = false)
    var discountRate: Int = discountRate
        protected set

    override val discountAmount: String
        get() = discountRate.toString() + "원"

    override fun doDiscount(totalPrice: Int): Int {
        return totalPrice - (totalPrice * this.discountRate / 100)
    }

}