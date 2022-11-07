package com.study.kotlkotlin.domain.order.domain

import com.study.kotlkotlin.domain.item.domain.Item
import javax.persistence.*

@Entity
class OrderItem(
    count: Int,
    totalPrice: Int,
    item: Item
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    var id: Long = 0
        protected set

    var count: Int = count
        protected set

    var totalPrice: Int = totalPrice
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id")
    var item: Item = item
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    lateinit var orders: Order
        protected set

    fun cancel() {
        this.item.addStock(count)
    }

}