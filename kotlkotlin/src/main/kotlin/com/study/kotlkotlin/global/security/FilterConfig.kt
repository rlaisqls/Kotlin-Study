package com.study.kotlkotlin.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.kotlkotlin.global.error.GlobalExceptionFilter
import com.study.kotlkotlin.global.security.jwt.JwtFilter
import com.study.kotlkotlin.global.security.jwt.JwtTokenProvider
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val objectMapper: ObjectMapper
): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>(){

    @Override
    public override fun configure(http: HttpSecurity) {

        http
            .addFilterBefore(JwtFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(GlobalExceptionFilter(objectMapper), JwtFilter::class.java)
    }

}