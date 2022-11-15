package com.study.kotlkotlin.domain.order.presentation.dto.request

import javax.validation.constraints.NotNull

data class DoOrderRequest(

    @field:NotNull
    val orderItemList: List<OrderItemRequest>
) {
    data class OrderItemRequest(

        @field:NotNull
        val itemId: Long,

        @field:NotNull
        val count: Int,

        @field:NotNull
        val userCouponId: Long?
    )
}