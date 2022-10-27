package com.study.kotlkotlin.domain.coupon.facde

import com.study.kotlkotlin.domain.coupon.domain.UserCoupon
import com.study.kotlkotlin.domain.coupon.domain.repository.UserCouponRepository
import com.study.kotlkotlin.domain.coupon.error.CouponNotFoundException
import com.study.kotlkotlin.domain.user.domain.User
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Component
class UserCouponFacade(
    private val userCouponRepository: UserCouponRepository
) {

    fun validateCoupon(userCoupon: UserCoupon): Boolean {
        if (LocalDateTime.now().isAfter(userCoupon.expirationDateTime)) {
            userCouponRepository.delete(userCoupon)
            return false
        }
        return true
    }

    fun getUserCoupon(userCouponId: Long, user: User): UserCoupon {

        val userCoupon = userCouponRepository.findById(userCouponId)
            .orElseThrow{ CouponNotFoundException.EXCEPTION }

        if (userCoupon.user !== user) throw CouponNotFoundException.EXCEPTION

        return userCoupon
    }
}