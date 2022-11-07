package com.study.kotlkotlin.domain.user.service

import com.study.kotlkotlin.domain.user.domain.User
import com.study.kotlkotlin.domain.user.domain.enums.Authority
import com.study.kotlkotlin.domain.user.domain.repository.UserRepository
import com.study.kotlkotlin.domain.user.facade.UserFacade
import com.study.kotlkotlin.domain.user.presentation.dto.request.ChangePasswordRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.password.PasswordEncoder

@ExtendWith(MockitoExtension::class)
internal class ChangePasswordServiceTest{

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var userFacade: UserFacade

    @Mock
    private lateinit var passwordEncoder: PasswordEncoder

    @InjectMocks
    private lateinit var changePasswordService: ChangePasswordService

    private val username: String = "rlaisqls"
    private val oldPassword: String = "password"
    private val newPassword: String = "newPassword"
    private val authority: Authority = Authority.USER

    private val userStub: User by lazy {
        User(
            username = username,
            password = oldPassword,
            authority = authority
        )
    }

    @Test
    fun 비밀번호_변경_성공() {
        //given
        given(userFacade.getCurrentUser()).willReturn(userStub)
        val request = ChangePasswordRequest(
            oldPassword = oldPassword,
            newPassword = newPassword
        )
        //when
        changePasswordService.execute(request)
        //then
        then(userFacade).should(times(1)).checkPassword(userStub, oldPassword)
        then(userRepository).should(times(1)).save(userStub)
    }

}