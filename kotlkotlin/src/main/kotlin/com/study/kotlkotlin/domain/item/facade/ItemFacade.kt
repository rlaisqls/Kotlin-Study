package com.study.kotlkotlin.domain.item.facade

import com.study.kotlkotlin.domain.item.domain.Item
import com.study.kotlkotlin.domain.item.domain.repository.ItemRepository
import com.study.kotlkotlin.domain.item.error.ItemNotFoundException
import org.springframework.stereotype.Component
import java.util.function.Supplier


@Component
class ItemFacade(
    private val itemRepository: ItemRepository
) {

    fun getItemById(itemId: Long): Item {
        return itemRepository.getById(itemId) ?: throw ItemNotFoundException.EXCEPTION
    }
}