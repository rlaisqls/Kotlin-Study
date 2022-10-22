package com.study.kotlkotlin.domain.coupon.domain

import com.study.kotlkotlin.domain.user.domain.User
import java.time.LocalDateTime
import javax.persistence.*


@Entity
class UserCoupon(
    user: User,
    coupon: Coupon,
    expirationDateTime: LocalDateTime
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_coupon_id", nullable = false)
    var id: Long = 0
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User = user
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    var coupon: Coupon = coupon
        protected set

    var expirationDateTime: LocalDateTime = expirationDateTime
        protected set

}