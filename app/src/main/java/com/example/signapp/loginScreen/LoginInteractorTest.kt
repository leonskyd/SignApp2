package com.example.signapp.loginScreen

import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LoginInteractorTest {
    val loginInteractor = LoginInteractor()

    @Test
    fun isLoginExist_CorrectLogin_ReturnsTrue() {
        assertTrue(loginInteractor.isLoginExist("golovin"))
    }
    @Test
    fun isLoginExist_WrongLogin_ReturnsFalse() {
        assertFalse(loginInteractor.isLoginExist("golovina"))
    }
    @Test
    fun isLoginExist_AlmostCorrectLogin_ReturnsFalse() {
        assertFalse(loginInteractor.isLoginExist(" golovin"))
    }
    @Test
    fun isLoginExist_GibberishLogin_ReturnsFalse() {
        assertFalse(loginInteractor.isLoginExist("flsdhowif11__%%$22"))
    }
    @Test
    fun isLoginExist_EmptyLogin_ReturnsFalse() {
        assertFalse(loginInteractor.isLoginExist(" "))
    }


    @Test
    fun checkPassword_CorrectCombination_ReturnsTrue() {
        assertTrue(loginInteractor.checkPassword("mitin", "34567"))
    }
    @Test
    fun checkPassword_CorrectLoginOtherUserPassword_ReturnsFalse() {
        assertFalse(loginInteractor.checkPassword("mitin", "12345"))
    }
    @Test
    fun checkPassword_CorrectLoginAlmostCorrectPassword_ReturnsFalse() {
        assertFalse(loginInteractor.checkPassword("mitin", " 34567"))
    }
    @Test
    fun checkPassword_UpperCaseLoginCorrectPassword_ReturnsFalse() {
        assertFalse(loginInteractor.checkPassword("MITIN", "34567"))
    }
}
