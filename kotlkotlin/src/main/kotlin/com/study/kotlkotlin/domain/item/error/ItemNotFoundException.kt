package com.study.kotlkotlin.domain.item.error

import com.study.kotlkotlin.global.error.exception.BusinessException

class ItemNotFoundException private constructor(): BusinessException(ItemErrorCode.ITEM_NOT_FOUND){

    companion object {
        @JvmField
        val EXCEPTION = ItemNotFoundException()
    }
}