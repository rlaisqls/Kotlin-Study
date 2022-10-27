package com.study.kotlkotlin.domain.coupon.service

import com.study.kotlkotlin.domain.coupon.domain.Coupon
import com.study.kotlkotlin.domain.coupon.domain.repository.CouponRepository
import com.study.kotlkotlin.domain.coupon.presentation.dto.request.CreateCouponRequest
import com.study.kotlkotlin.domain.coupon.presentation.dto.response.CreateCouponResponse
import org.springframework.stereotype.Service


@Service
class CreateCouponService(
    private val couponRepository: CouponRepository
) {
    fun execute(request: CreateCouponRequest): CreateCouponResponse {

        val coupon = couponRepository.save<Coupon>(Coupon.of(
            discountType = request.discountType,
            name = request.couponName,
            validityPeriod = request.validityPeriod,
            discountAmount = request.discountAmount
        ))
        return CreateCouponResponse(coupon.id)
    }
}