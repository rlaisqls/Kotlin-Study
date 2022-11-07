package com.study.kotlkotlin.domain.order.presentation.dto.request

import javax.validation.Valid

data class DoOrderRequest(
    @field:Valid
    val orderItemList: List<OrderItemRequest>
) {
    data class OrderItemRequest(
        val itemId: Long,
        val count: Int,
        val userCouponId: Long?
    )
}