package com.study.kotlkotlin.domain.order.service

import com.study.kotlkotlin.domain.coupon.domain.UserCoupon
import com.study.kotlkotlin.domain.coupon.domain.repository.UserCouponRepository
import com.study.kotlkotlin.domain.coupon.error.ExpiredCouponException
import com.study.kotlkotlin.domain.coupon.facde.UserCouponFacade
import com.study.kotlkotlin.domain.item.facade.ItemFacade
import com.study.kotlkotlin.domain.order.domain.Order
import com.study.kotlkotlin.domain.order.domain.OrderItem
import com.study.kotlkotlin.domain.order.domain.enums.OrderStatus
import com.study.kotlkotlin.domain.order.domain.repository.OrderRepository
import com.study.kotlkotlin.domain.order.presentation.dto.request.DoOrderRequest
import com.study.kotlkotlin.domain.order.presentation.dto.request.DoOrderRequest.OrderItemRequest
import com.study.kotlkotlin.domain.order.presentation.dto.response.DoOrderResponse
import com.study.kotlkotlin.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
class DoOrderService(
    private val userFacade: UserFacade,
    private val orderRepository: OrderRepository,
    private val userCouponFacade: UserCouponFacade,
    private val userCouponRepository: UserCouponRepository,
    private val itemFacade: ItemFacade
) {
    @Transactional
    fun execute(requests: DoOrderRequest): DoOrderResponse {

        val user = userFacade.getCurrentUser()

        val orderItems: List<OrderItem> = requests.orderItemList
            .map { createOrderItem(it) }

        val order = orderRepository.save(Order(
            user = user,
            orderStatus = OrderStatus.ORDER,
            orderItems = orderItems
        ))

        return DoOrderResponse(
            orderId = order.id,
            totalPrice = order.totalPrice
        )
    }

    private fun createOrderItem(request: OrderItemRequest): OrderItem {

        val item = itemFacade.getItemById(request.itemId)
        item.removeStock(request.count)

        var totalPrice = item.price * request.count

        request.userCouponId?.let {

            val userCoupon = validateAndGetCoupon(it)

            totalPrice = userCoupon.coupon.doDiscount(totalPrice)
            userCouponRepository.delete(userCoupon)
        }

        return OrderItem(
            item = item,
            count = request.count,
            totalPrice = totalPrice
        )
    }

    private fun validateAndGetCoupon(userCouponId: Long): UserCoupon {

        val user = userFacade.getCurrentUser()
        val userCoupon: UserCoupon = userCouponFacade.getUserCoupon(userCouponId, user)

        if (!userCouponFacade.validateCoupon(userCoupon)) throw ExpiredCouponException.EXCEPTION
        return userCoupon
    }
}