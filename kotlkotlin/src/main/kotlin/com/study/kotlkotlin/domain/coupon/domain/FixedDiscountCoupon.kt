package com.study.kotlkotlin.domain.coupon.domain

import com.study.kotlkotlin.domain.coupon.domain.enums.DiscountType
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.validation.constraints.Size

@DiscriminatorValue(DiscountType.Values.FIXED)
@Entity
class FixedDiscountCoupon(
    name: String,
    validityPeriod: Int,
    discountPrice: Int
): Coupon(name = name, validityPeriod = validityPeriod) {

    @Size(min = 0)
    @Column(nullable = false)
    var discountPrice: Int = discountPrice
        protected set

    override val discountAmount: String
        get() = discountPrice.toString() + "원"

    override fun doDiscount(totalPrice: Int): Int {
        return if (totalPrice < this.discountPrice) 0
                else totalPrice - this.discountPrice
    }

}