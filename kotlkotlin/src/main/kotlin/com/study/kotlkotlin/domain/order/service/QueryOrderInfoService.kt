package com.study.kotlkotlin.domain.order.service

import com.study.kotlkotlin.domain.order.error.OrderNotFoundException
import com.study.kotlkotlin.domain.order.facade.OrderFacade
import com.study.kotlkotlin.domain.order.presentation.dto.response.QueryOrderInfoResponse
import com.study.kotlkotlin.domain.user.facade.UserFacade
import javax.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class QueryOrderInfoService(
    private val userFacade: UserFacade,
    private val orderFacade: OrderFacade
) {
    @Transactional
    fun execute(orderId: Long): QueryOrderInfoResponse {
        val user = userFacade.getCurrentUser()
        val order = orderFacade.findById(orderId)

        if (order.user !== user) {
            throw OrderNotFoundException.EXCEPTION
        }
        return QueryOrderInfoResponse(order)
    }
}