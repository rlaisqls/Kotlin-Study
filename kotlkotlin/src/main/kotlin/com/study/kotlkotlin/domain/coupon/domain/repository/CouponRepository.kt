package com.study.kotlkotlin.domain.coupon.domain.repository

import com.study.kotlkotlin.domain.coupon.domain.Coupon
import org.springframework.data.repository.CrudRepository

interface CouponRepository: CrudRepository<Coupon, Long> {
}