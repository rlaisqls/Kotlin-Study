package com.study.kotlkotlin.domain.item.presentation.dto.request

import org.hibernate.validator.constraints.Length

data class CreateItemRequest(

    @field:Length(min = 1, max = 30, message = "itemName는 30자 이하여야 합니다.")
    val itemName: String,
    val price: Int,
    val stock: Int,
    val description: String
)