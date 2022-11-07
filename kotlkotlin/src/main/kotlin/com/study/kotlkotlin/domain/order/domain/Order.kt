package com.study.kotlkotlin.domain.order.domain

import com.study.kotlkotlin.domain.order.domain.enums.OrderStatus
import com.study.kotlkotlin.domain.user.domain.User
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*


@EntityListeners(AuditingEntityListener::class)
@Table(name = "orders")
@Entity
class Order(
    user: User,
    orderStatus: OrderStatus,
    orderItems: List<OrderItem>
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    var id: Long = 0
        protected set

    @Column(nullable = false)
    var orderStatus: OrderStatus = orderStatus
        protected set

    @CreatedDate
    @Column(nullable = false, updatable = false)
    lateinit var orderDateTime: LocalDateTime
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    var user: User = user
        protected set

    @OneToMany(mappedBy = "orders", cascade = [CascadeType.ALL])
    var orderItems: MutableList<OrderItem> = mutableListOf()
        protected set

    @get:Transient
    val totalPrice: Int
        get() = orderItems.sumOf { it.totalPrice }

    fun cancel() {
        orderStatus = OrderStatus.CANCEL
        for (orderItem in orderItems) {
            orderItem.cancel()
        }
    }
}