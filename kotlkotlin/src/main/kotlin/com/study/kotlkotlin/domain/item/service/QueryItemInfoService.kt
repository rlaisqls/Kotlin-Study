package com.study.kotlkotlin.domain.item.service

import com.study.kotlkotlin.domain.item.facade.ItemFacade
import com.study.kotlkotlin.domain.item.presentation.dto.response.QueryItemInfoResponse
import org.springframework.stereotype.Service

@Service
class QueryItemInfoService(
    private val itemFacade: ItemFacade
) {
    fun execute(itemId: Long): QueryItemInfoResponse {

        val item = itemFacade.getItemById(itemId)
        return QueryItemInfoResponse(item)
    }
}