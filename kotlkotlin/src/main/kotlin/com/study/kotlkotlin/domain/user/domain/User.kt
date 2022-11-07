package com.study.kotlkotlin.domain.user.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.study.kotlkotlin.domain.order.domain.Order
import com.study.kotlkotlin.domain.user.domain.enums.Authority
import javax.persistence.*


@Entity
class User(
    username: String,
    password: String,
    authority: Authority
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    var id: Long = 0
        protected set

    @Column(nullable = false, unique = true, length = 15)
    var username: String = username
        protected set

    @Column(nullable = false, length = 60)
    var password: String = password
        protected set

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    var authority: Authority = authority
        protected set

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private val orders: MutableList<Order> = ArrayList()

    fun changePassword(encode: String?) {
        this.password = password
    }

}