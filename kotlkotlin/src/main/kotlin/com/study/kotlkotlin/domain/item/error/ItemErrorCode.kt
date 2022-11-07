package com.study.kotlkotlin.domain.item.error

import com.study.kotlkotlin.global.error.exception.ErrorProperty

enum class ItemErrorCode (
    private val status: Int,
    private val message: String
): ErrorProperty {

    ITEM_NOT_FOUND(404, "Item Not Found"),
    STOCK_NOT_ENOUGH(404, "Stock Not Enough");

    override fun message(): String = message
    override fun status(): Int = status
}