package com.study.kotlkotlin.domain.item.presentation.dto.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull

data class CreateItemRequest(

    @field:NotNull
    @field:Length(min = 1, max = 30)
    val itemName: String,

    @field:NotNull
    val price: Int,

    @field:NotNull
    val stock: Int,

    @field:NotNull
    val description: String
)