package com.study.kotlkotlin.domain.order.presentation.dto.response

import com.study.kotlkotlin.domain.order.domain.Order
import com.study.kotlkotlin.domain.order.domain.enums.OrderStatus
import java.time.LocalDateTime
import javax.validation.Valid


data class QueryOrderListResponse(
    @field:Valid
    val orderList: List<OrderResponse>
) {
    data class OrderResponse(
        val orderId: Long,
        val orderStatus: OrderStatus,
        val orderDateTime: LocalDateTime,
        val representativeItemName: String,
        val totalItemCount: Int
    ) {
        constructor(order: Order): this(
            orderId = order.id,
            orderStatus = order.orderStatus,
            orderDateTime = order.orderDateTime,
            representativeItemName = order.orderItems[0].item.name,
            totalItemCount = order.orderItems.size
        )
    }
}