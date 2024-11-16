package com.example.weightogo.TestCase

import org.junit.Assert.assertNotEquals
import org.junit.Test

class SecurityTest {

    @Test
    fun testPasswordEncryption() {
        val rawPassword = "password123"
        val encryptedPassword = encryptPassword(rawPassword)
        assertNotEquals(rawPassword, encryptedPassword)
    }

    @Test
    fun testUnauthorizedAccess() {
        val isAuthorized = checkUserAuthorization("unauthorizedUser")
        assertEquals(false, isAuthorized)
    }

    private fun encryptPassword(password: String): String {
        // Mock encryption function
        return password.reversed() + "encrypted"
    }

    private fun checkUserAuthorization(userId: String): Boolean {
        // Mock unauthorized user check
        return userId != "unauthorizedUser"
    }
}
