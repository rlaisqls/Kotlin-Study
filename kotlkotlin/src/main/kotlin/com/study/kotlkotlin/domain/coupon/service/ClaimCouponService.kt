package com.study.kotlkotlin.domain.coupon.service

import com.study.kotlkotlin.domain.coupon.domain.UserCoupon
import com.study.kotlkotlin.domain.coupon.domain.repository.CouponRepository
import com.study.kotlkotlin.domain.coupon.domain.repository.UserCouponRepository
import com.study.kotlkotlin.domain.coupon.error.CouponNotFoundException
import com.study.kotlkotlin.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class ClaimCouponService(
    val userFacade: UserFacade,
    val couponRepository: CouponRepository,
    val userCouponRepository: UserCouponRepository
) {
    fun execute(couponId: Long) {

        val user = userFacade.getCurrentUser()
        val coupon = couponRepository.findById(couponId).orElseThrow { CouponNotFoundException.EXCEPTION }

        userCouponRepository.save(
            UserCoupon(
                user = user,
                coupon = coupon,
                expirationDateTime = LocalDateTime.now().plusMinutes(coupon.validityPeriod.toLong())
            )
        )
    }
}