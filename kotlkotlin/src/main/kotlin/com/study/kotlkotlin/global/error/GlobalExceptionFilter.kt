package com.study.kotlkotlin.global.error

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.kotlkotlin.global.error.dto.ErrorResponse
import com.study.kotlkotlin.global.error.exception.BusinessException
import com.study.kotlkotlin.global.error.exception.ErrorProperty
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest

import javax.servlet.http.HttpServletResponse

class GlobalExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: BusinessException) {
            val errorCode: ErrorProperty = e.errorProperty
            response.status = errorCode.status()
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            val errorResponse: ErrorResponse = ErrorResponse.of(errorCode)
            objectMapper.writeValue(response.writer, errorResponse)
        }
    }
}