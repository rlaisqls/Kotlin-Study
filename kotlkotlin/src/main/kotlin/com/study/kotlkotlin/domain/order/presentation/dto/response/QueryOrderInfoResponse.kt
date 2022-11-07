package com.study.kotlkotlin.domain.order.presentation.dto.response

import com.study.kotlkotlin.domain.order.domain.Order
import com.study.kotlkotlin.domain.order.domain.OrderItem
import com.study.kotlkotlin.domain.order.domain.enums.OrderStatus
import java.time.LocalDateTime

data class QueryOrderInfoResponse(
    val orderId: Long,
    val orderStatus: OrderStatus,
    val orderDateTime: LocalDateTime,
    val orderItems: List<OrderItemResponse>,
    val totalPrice: Int
) {

    constructor(order: Order) : this(
        orderId = order.id,
        orderStatus = order.orderStatus,
        orderDateTime = order.orderDateTime,
        orderItems = order.orderItems.map { OrderItemResponse(it) },
        totalPrice = order.totalPrice
    )

    data class OrderItemResponse(
        val itemName: String,
        val count: Int
    ) {
        constructor(orderItem: OrderItem) : this(
            itemName = orderItem.item.name,
            count = orderItem.count
        )
    }
}