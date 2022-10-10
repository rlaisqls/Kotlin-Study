package com.study.kotlkotlin.global.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
class JwtProperties(
    val header: String,
    val prefix: String,
    val secretKey: String,
    val accessExp: Long,
    val refreshExp: Long
)