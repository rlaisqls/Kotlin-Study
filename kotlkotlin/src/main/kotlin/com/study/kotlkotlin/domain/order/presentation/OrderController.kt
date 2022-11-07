package com.study.kotlkotlin.domain.order.presentation

import com.study.kotlkotlin.domain.order.presentation.dto.request.DoOrderRequest
import com.study.kotlkotlin.domain.order.presentation.dto.response.DoOrderResponse
import com.study.kotlkotlin.domain.order.presentation.dto.response.QueryOrderInfoResponse
import com.study.kotlkotlin.domain.order.presentation.dto.response.QueryOrderListResponse
import com.study.kotlkotlin.domain.order.service.CancelOrderService
import com.study.kotlkotlin.domain.order.service.DoOrderService
import com.study.kotlkotlin.domain.order.service.QueryOrderInfoService
import com.study.kotlkotlin.domain.order.service.QueryOrderListService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/orders")
class OrderController(
    val doOrderService: DoOrderService,
    val cancelOrderService: CancelOrderService,
    val queryOrderInfoService: QueryOrderInfoService,
    val queryOrderListService: QueryOrderListService,
) {

    @PostMapping
    fun doOrder(@Valid @RequestBody requests: DoOrderRequest): DoOrderResponse {
        return doOrderService.execute(requests)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{order-id}")
    fun cancelOrder(@PathVariable("order-id") orderId: Long?) {
        cancelOrderService.execute(orderId!!)
    }

    @GetMapping
    fun queryOrderList(): QueryOrderListResponse {
        return queryOrderListService.execute()
    }

    @GetMapping("/{order-id}")
    fun queryOrderInfo(@PathVariable("order-id") orderId: Long?): QueryOrderInfoResponse {
        return queryOrderInfoService.execute(orderId!!)
    }
}