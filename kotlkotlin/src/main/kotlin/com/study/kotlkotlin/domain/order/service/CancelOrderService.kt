package com.study.kotlkotlin.domain.order.service

import com.study.kotlkotlin.domain.order.domain.repository.OrderRepository
import com.study.kotlkotlin.domain.order.error.OrderNotFoundException
import com.study.kotlkotlin.domain.order.facade.OrderFacade
import com.study.kotlkotlin.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CancelOrderService(
    private val userFacade: UserFacade,
    private val orderFacade: OrderFacade,
    private val orderRepository: OrderRepository
) {
    @Transactional
    fun execute(id: Long) {
        val user = userFacade.getCurrentUser()
        val order = orderFacade.findById(id)

        if (order.user !== user) {
            throw OrderNotFoundException.EXCEPTION
        }
        order.cancel()
        orderRepository.save(order)
    }
}