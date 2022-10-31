package com.study.kotlkotlin.domain.user.service

import com.study.kotlkotlin.domain.user.facade.UserFacade
import com.study.kotlkotlin.domain.user.presentation.dto.response.QueryUserInfoResponse
import org.springframework.stereotype.Service

@Service
class QueryUserInfoService(
    private val userFacade: UserFacade
) {
    fun execute(userId: Long): QueryUserInfoResponse {

        val user = userFacade.findById(userId)
        return QueryUserInfoResponse(
            userId = user.id,
            username = user.username
        )
    }
}