package com.study.kotlkotlin.domain.order.facade

import com.study.kotlkotlin.domain.order.domain.Order
import com.study.kotlkotlin.domain.order.domain.repository.OrderRepository
import com.study.kotlkotlin.domain.order.error.OrderNotFoundException
import org.springframework.stereotype.Component


@Component
class OrderFacade(
    private val orderRepository: OrderRepository
) {
    fun findById(id: Long): Order {
        return orderRepository.findById(id)
            .orElseThrow { OrderNotFoundException.EXCEPTION }
    }
}