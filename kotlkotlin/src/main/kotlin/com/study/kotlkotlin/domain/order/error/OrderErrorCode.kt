package com.study.kotlkotlin.domain.order.error

import com.study.kotlkotlin.global.error.exception.ErrorProperty

enum class OrderErrorCode (
    private val status: Int,
    private val message: String
): ErrorProperty {

    ORDER_NOT_FOUND(404, "Order Not Found"),
    ITEM_NOT_FOUND(404, "Item Not Found"),
    STOCK_NOT_ENOUGH(404, "Stock Not Enough");

    override fun message(): String = message
    override fun status(): Int = status
}