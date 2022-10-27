package com.study.kotlkotlin.domain.coupon.presentation

import com.study.kotlkotlin.domain.coupon.presentation.dto.request.CreateCouponRequest
import com.study.kotlkotlin.domain.coupon.presentation.dto.response.CreateCouponResponse
import com.study.kotlkotlin.domain.coupon.presentation.dto.response.QueryUserCouponListResponse
import com.study.kotlkotlin.domain.coupon.service.ClaimCouponService
import com.study.kotlkotlin.domain.coupon.service.CreateCouponService
import com.study.kotlkotlin.domain.coupon.service.QueryMyCouponListService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RequestMapping("/coupons")
@RestController
class CouponController(
    val createCouponService: CreateCouponService,
    val claimCouponService: ClaimCouponService,
    val queryMyCouponListService: QueryMyCouponListService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createCoupon(@Valid @RequestBody request: CreateCouponRequest?): CreateCouponResponse {
        return createCouponService.execute(request!!)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{couponId}")
    fun claimCoupon(@PathVariable couponId: Long?) {
        claimCouponService.execute(couponId!!)
    }

    @GetMapping
    fun findMyCouponList(): QueryUserCouponListResponse {
        return queryMyCouponListService.execute()
    }
}