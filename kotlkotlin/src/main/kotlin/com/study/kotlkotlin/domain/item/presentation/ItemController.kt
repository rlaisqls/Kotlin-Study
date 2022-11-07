package com.study.kotlkotlin.domain.item.presentation

import com.study.kotlkotlin.domain.item.presentation.dto.request.CreateItemRequest
import com.study.kotlkotlin.domain.item.presentation.dto.request.ModifyItemInfoRequest
import com.study.kotlkotlin.domain.item.presentation.dto.response.QueryItemInfoResponse
import com.study.kotlkotlin.domain.item.presentation.dto.response.QueryItemListResponse
import com.study.kotlkotlin.domain.item.service.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/items")
class ItemController(
    val createItemService: CreateItemService,
    val modifyItemService: ModifyItemInfoService,
    val deleteItemService: DeleteItemService,
    val queryItemInfoService: QueryItemInfoService,
    val queryItemListService: QueryItemListService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createItem(@Valid @RequestBody request: CreateItemRequest) {
        createItemService.execute(request)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{item-id}")
    fun modifyItem(@PathVariable("item-id") itemId: Long, @Valid @RequestBody request: ModifyItemInfoRequest) {
        modifyItemService.execute(itemId, request)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{item-id}")
    fun deleteItem(@PathVariable("item-id") itemId: Long?) {
        deleteItemService.execute(itemId!!)
    }

    @GetMapping
    fun queryItemList(): QueryItemListResponse {
        return queryItemListService.execute()
    }

    @GetMapping("/{itemId}")
    fun queryItemInfo(@PathVariable itemId: Long): QueryItemInfoResponse {
        return queryItemInfoService.execute(itemId)
    }
}