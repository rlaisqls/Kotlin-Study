package com.study.kotlkotlin.domain.item.domain.repository

import com.study.kotlkotlin.domain.item.domain.Item
import org.springframework.data.repository.CrudRepository


interface ItemRepository : CrudRepository<Item, Long> {
    fun findBy(): List<Item>
    fun getById(id: Long): Item?
}