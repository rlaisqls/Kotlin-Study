package com.study.kotlkotlin.domain.coupon.domain

import com.study.kotlkotlin.domain.coupon.domain.enums.DiscountType
import javax.persistence.*

@DiscriminatorColumn(name = "discountType", discriminatorType = DiscriminatorType.STRING)
@Entity
abstract class Coupon(
    name: String,
    validityPeriod: Int
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    var id: Long = 0
        protected set

    @Column(nullable = false, unique = true, length = 20)
    var name: String = name
        protected set

    @Column(nullable = false)
    var validityPeriod: Int = validityPeriod
        protected set

    @get:Transient
    abstract val discountAmount: String

    abstract fun doDiscount(totalPrice: Int): Int

    companion object {
        fun of(discountType: DiscountType, name: String, validityPeriod: Int, discountAmount: Int): Coupon {

            val coupon: Coupon = when(discountType) {
                DiscountType.FIXED ->
                    FixedDiscountCoupon(name, validityPeriod, discountPrice = discountAmount)
                DiscountType.RATE ->
                    RateDiscountCoupon(name, validityPeriod, discountRate = discountAmount)
            }
            return coupon
        }
    }

}