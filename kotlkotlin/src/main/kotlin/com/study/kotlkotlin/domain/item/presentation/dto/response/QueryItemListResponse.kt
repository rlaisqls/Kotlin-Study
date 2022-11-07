package com.study.kotlkotlin.domain.item.presentation.dto.response

import com.study.kotlkotlin.domain.item.domain.Item

data class QueryItemListResponse(
    val itemList: List<ItemResponse>
) {
    data class ItemResponse(
        val id: Long,
        val name: String,
        val price: Int
    ) {
        constructor(item: Item): this(
            id = item.id,
            name = item.name,
            price = item.price
        )
    }
}