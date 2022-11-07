package com.study.kotlkotlin.domain.order.error

import com.study.kotlkotlin.global.error.exception.BusinessException

class OrderNotFoundException private constructor(): BusinessException(OrderErrorCode.ORDER_NOT_FOUND) {

    companion object {
        @JvmField
        val EXCEPTION = OrderNotFoundException()
    }
}