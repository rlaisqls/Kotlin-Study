package com.study.kotlkotlin.domain.coupon.domain.repository

import com.study.kotlkotlin.domain.coupon.domain.UserCoupon
import com.study.kotlkotlin.domain.user.domain.User
import org.springframework.data.repository.CrudRepository

interface UserCouponRepository: CrudRepository<UserCoupon, Long> {
    fun findByUser(user: User): List<UserCoupon>
}