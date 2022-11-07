package com.study.kotlkotlin.domain.user.facade

import com.study.kotlkotlin.domain.user.domain.User
import com.study.kotlkotlin.domain.user.domain.enums.Authority
import com.study.kotlkotlin.domain.user.domain.repository.UserRepository
import com.study.kotlkotlin.domain.user.error.UserAlreadyExistException
import com.study.kotlkotlin.domain.user.error.UserNotFoundException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*


@ExtendWith(MockitoExtension::class)
internal class UserFacadeTest {

    @Mock
    private lateinit var passwordEncoder: PasswordEncoder

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var userFacade: UserFacade

    private val username: String = "rlaisqls"
    private val password: String = "password"
    private val authority: Authority = Authority.USER

    private val userStub: User by lazy {
        User(
            username = username,
            password = password,
            authority = authority
        )
    }

    @Test
    fun findByUsername_성공() {
        //given
        given(userRepository.findByUsername(userStub.username)).willReturn(userStub)
        //when then
        assertDoesNotThrow {
            userFacade.findByUsername(userStub.username)
        }
    }

    @Test
    fun findByUsername_실패() {
        //given
        given(userRepository.findByUsername(userStub.username)).willReturn(null)
        //when then
        assertThrows<UserNotFoundException> {
            userFacade.findByUsername(userStub.username)
        }
    }

    @Test
    fun findById_성공() {
        //given
        given(userRepository.findById(userStub.id)).willReturn(Optional.of(userStub))
        //when then
        assertDoesNotThrow {
            userFacade.findById(userStub.id)
        }
    }

    @Test
    fun findById_실패() {
        //given
        given(userRepository.findById(userStub.id)).willReturn(Optional.empty())
        //when then
        assertThrows<UserNotFoundException> {
            userFacade.findById(userStub.id)
        }
    }

    @Test
    fun checkUsernameExist_성공() {
        //given
        given(userRepository.findByUsername(userStub.username)).willReturn(null)
        //when then
        assertDoesNotThrow {
            userFacade.checkUsernameExist(username)
        }
    }

    @Test
    fun checkUsernameExist_실패() {
        //given
        given(userRepository.findByUsername(userStub.username)).willReturn(userStub)
        //when then
        assertThrows<UserAlreadyExistException> {
            userFacade.checkUsernameExist(username)
        }
    }

    @Test
    fun getCurrentUser() {
        //given
        val context: SecurityContext = SecurityContextHolder.getContext()
        context.authentication = UsernamePasswordAuthenticationToken(userStub.username, null, null)
        given(userRepository.findByUsername(userStub.username)).willReturn(userStub)
        //when then
        assertEquals(userFacade.getCurrentUser(), userStub)
    }

}