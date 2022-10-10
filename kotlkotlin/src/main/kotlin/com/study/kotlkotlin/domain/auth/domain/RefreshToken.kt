package com.study.kotlkotlin.domain.auth.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

@RedisHash
class RefreshToken(
    username: String,
    token: String,
    expiration: Long
) {

    @Id
    var username: String = username
        protected set

    @Indexed
    var token: String = token
        protected set

    @TimeToLive
    var expiration: Long = expiration
        protected set

    fun updateToken(token: String, expiration: Long) {
        this.token = token
        this.expiration = expiration
    }
}