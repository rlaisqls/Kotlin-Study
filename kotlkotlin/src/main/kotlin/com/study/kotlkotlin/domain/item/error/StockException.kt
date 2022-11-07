package com.study.kotlkotlin.domain.item.error

import com.study.kotlkotlin.global.error.exception.BusinessException

class StockException private constructor(): BusinessException(ItemErrorCode.STOCK_NOT_ENOUGH){

    companion object {
        @JvmField
        val EXCEPTION = StockException()
    }
}