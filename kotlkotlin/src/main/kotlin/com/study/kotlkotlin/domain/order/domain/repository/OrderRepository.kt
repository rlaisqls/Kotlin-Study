package com.study.kotlkotlin.domain.order.domain.repository

import com.study.kotlkotlin.domain.order.domain.Order
import com.study.kotlkotlin.domain.user.domain.User
import org.springframework.data.repository.CrudRepository


interface OrderRepository : CrudRepository<Order, Long> {
    fun findByUser(user: User): List<Order>
}