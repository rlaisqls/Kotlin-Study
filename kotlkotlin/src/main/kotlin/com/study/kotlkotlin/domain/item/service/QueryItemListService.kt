package com.study.kotlkotlin.domain.item.service

import com.study.kotlkotlin.domain.item.domain.repository.ItemRepository
import com.study.kotlkotlin.domain.item.presentation.dto.response.QueryItemListResponse
import org.springframework.stereotype.Service

@Service
class QueryItemListService(
    private val itemRepository: ItemRepository
) {
    fun execute(): QueryItemListResponse {

        return QueryItemListResponse(
            itemRepository.findBy()
                .map { QueryItemListResponse.ItemResponse(it) }
        )
    }
}