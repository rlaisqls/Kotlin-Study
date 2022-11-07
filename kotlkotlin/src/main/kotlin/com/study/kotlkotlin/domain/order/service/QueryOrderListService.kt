package com.study.kotlkotlin.domain.order.service

import com.study.kotlkotlin.domain.order.domain.repository.OrderRepository
import com.study.kotlkotlin.domain.order.presentation.dto.response.QueryOrderListResponse
import com.study.kotlkotlin.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class QueryOrderListService(
    private val userFacade: UserFacade,
    private val orderRepository: OrderRepository
) {
    @Transactional
    fun execute(): QueryOrderListResponse {

        val user = userFacade.getCurrentUser()

        return QueryOrderListResponse(
            orderRepository.findByUser(user)
                .map { QueryOrderListResponse.OrderResponse(it) }
        )
    }
}