package com.study.kotlkotlin.domain.item.service

import com.study.kotlkotlin.domain.item.domain.repository.ItemRepository
import com.study.kotlkotlin.domain.item.facade.ItemFacade
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class DeleteItemService(
    private val itemFacade: ItemFacade,
    private val itemRepository: ItemRepository
) {
    @Transactional
    fun execute(itemId: Long) {

        val item = itemFacade.getItemById(itemId)
        itemRepository.delete(item)
    }
}