package com.study.kotlkotlin.domain.coupon.service

import com.study.kotlkotlin.domain.coupon.domain.repository.UserCouponRepository
import com.study.kotlkotlin.domain.coupon.facde.UserCouponFacade
import com.study.kotlkotlin.domain.coupon.presentation.dto.response.QueryUserCouponListResponse
import com.study.kotlkotlin.domain.coupon.presentation.dto.response.QueryUserCouponListResponse.UserCouponResponse
import com.study.kotlkotlin.domain.user.domain.User
import com.study.kotlkotlin.domain.user.facade.UserFacade
import org.springframework.stereotype.Service

@Service
class QueryMyCouponListService(
    private val userFacade: UserFacade,
    private val userCouponFacade: UserCouponFacade,
    private val userCouponRepository: UserCouponRepository
) {
    fun execute(): QueryUserCouponListResponse {

        val user: User = userFacade.getCurrentUser()

        return QueryUserCouponListResponse(
            userCouponRepository.findByUser(user)
                .filter { userCouponFacade.validateCoupon(it) }
                .map { UserCouponResponse(it) }
        )
    }
}