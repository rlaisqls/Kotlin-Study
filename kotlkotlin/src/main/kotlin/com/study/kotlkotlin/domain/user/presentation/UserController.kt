package com.study.kotlkotlin.domain.user.presentation

import com.study.kotlkotlin.domain.auth.service.TokenRefreshService
import com.study.kotlkotlin.domain.auth.presentation.dto.response.TokenResponse
import com.study.kotlkotlin.domain.user.presentation.dto.request.ChangePasswordRequest
import com.study.kotlkotlin.domain.user.presentation.dto.request.SignInRequest
import com.study.kotlkotlin.domain.user.presentation.dto.request.SignUpRequest
import com.study.kotlkotlin.domain.user.presentation.dto.response.QueryUserInfoResponse
import com.study.kotlkotlin.domain.user.service.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("/users")
@RestController
class UserController(
    private val signUpService: SignUpService,
    private val signInService: SignInService,
    private val tokenRefreshService: TokenRefreshService,
    private val changePasswordService: ChangePasswordService,
    private val queryMyInfoService: QueryMyInfoService,
    private val queryUserInfoService: QueryUserInfoService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun signUp(@Valid @RequestBody request: SignUpRequest): TokenResponse {
        return signUpService.execute(request)
    }

    @PostMapping("/auth")
    fun signIn(@Valid @RequestBody request: SignInRequest): TokenResponse {
        return signInService.execute(request)
    }

    @PutMapping("/auth")
    fun tokenRefresh(@RequestHeader("X-Refresh-Token") refreshToken: String): TokenResponse {
        return tokenRefreshService.execute(refreshToken)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    fun changePassword(@Valid @RequestBody request: ChangePasswordRequest) {
        changePasswordService.execute(request)
    }

    @GetMapping
    fun queryMyInformation(): QueryUserInfoResponse {
        return queryMyInfoService.execute()
    }

    @GetMapping("/{user-id}")
    fun queryUserInformation(@PathVariable("user-id") userId: Long): QueryUserInfoResponse {
        return queryUserInfoService.execute(userId)
    }

}