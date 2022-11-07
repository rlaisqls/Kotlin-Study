package com.study.kotlkotlin.domain.item.presentation.dto.response

import com.study.kotlkotlin.domain.item.domain.Item

data class QueryItemInfoResponse(
    val id: Long,
    val name: String,
    val price: Int,
    val stock: Int,
    val description: String
) {
    val isSoldOut: Boolean
     get() = stock == 0

    constructor(item: Item): this(
        id = item.id,
        name = item.name,
        price = item.price,
        stock = item.stock,
        description = item.description
    )

}