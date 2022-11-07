package com.study.kotlkotlin.domain.item.service

import com.study.kotlkotlin.domain.item.domain.Item
import com.study.kotlkotlin.domain.item.domain.repository.ItemRepository
import com.study.kotlkotlin.domain.item.presentation.dto.request.CreateItemRequest
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CreateItemService(
    private val itemRepository: ItemRepository
) {
    @Transactional
    fun execute(request: CreateItemRequest) {

        itemRepository.save(
            Item(
                name = request.itemName,
                price = request.price,
                stock = request.stock,
                description = request.description
            )
        )
    }
}