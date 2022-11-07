package com.study.kotlkotlin.domain.item.service

import com.study.kotlkotlin.domain.item.domain.repository.ItemRepository
import com.study.kotlkotlin.domain.item.facade.ItemFacade
import com.study.kotlkotlin.domain.item.presentation.dto.request.ModifyItemInfoRequest
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ModifyItemInfoService(
    private val itemFacade: ItemFacade,
    private val itemRepository: ItemRepository
) {
    @Transactional
    fun execute(itemId: Long, request: ModifyItemInfoRequest) {

        val item = itemFacade.getItemById(itemId)
        item.modifyInfo(
            name = request.itemName,
            price = request.price,
            stock = request.stock,
            description = request.description
        )
        itemRepository.save(item)
    }
}