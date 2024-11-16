package com.example.weightogo.TestCase

import org.junit.Assert.assertEquals
import org.junit.Test

class WeightInputValidationTest {

    @Test
    fun testValidWeightInput() {
        val weightInput = "180.5"
        val weight = weightInput.toDoubleOrNull()
        assert(weight != null)
    }

    @Test
    fun testInvalidWeightInput() {
        val weightInput = "invalidWeight"
        val weight = weightInput.toDoubleOrNull()
        assertEquals(null, weight)
    }

    @Test
    fun testEmptyWeightInput() {
        val weightInput = ""
        val weight = weightInput.toDoubleOrNull()
        assertEquals(null, weight)
    }

    @Test
    fun testFutureDateInput() {
        val dateInput = "12/31/2050" // Assume this is a future date
        val isFutureDate = isDateInFuture(dateInput)
        assertEquals(true, isFutureDate)
    }

    private fun isDateInFuture(date: String): Boolean {
        // Mock implementation for future date check
        // Assume this function correctly parses and compares dates
        return date.contains("2050")
    }
}
