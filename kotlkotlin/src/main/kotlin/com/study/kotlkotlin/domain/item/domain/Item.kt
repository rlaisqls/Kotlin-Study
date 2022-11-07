package com.study.kotlkotlin.domain.item.domain

import com.study.kotlkotlin.domain.item.error.StockException
import javax.persistence.*

@Entity
class Item(
    name: String,
    price: Int,
    stock: Int,
    description: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    var id: Long = 0
        protected set

    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var price: Int  = price
        protected set

    @Column(nullable = false)
    var stock: Int = stock
        protected set

    @Column(nullable = false)
    var description: String = description
        protected set

    fun modifyInfo(name: String, price: Int, stock: Int, description: String) {
        this.name = name
        this.price = price
        this.stock = stock
        this.description = description
    }

    fun addStock(quantity: Int) {
        stock += quantity
    }

    fun removeStock(quantity: Int) {
        val restStock = stock - quantity
        if (restStock < 0) {
            throw StockException.EXCEPTION
        }
        stock = restStock
    }
}