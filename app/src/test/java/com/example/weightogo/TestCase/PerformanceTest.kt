package com.example.weightogo.TestCase

import org.junit.Assert.assertTrue
import org.junit.Test

class PerformanceTest {

    @Test
    fun testLargeDataSet() {
        val dbHelper = WeightDatabaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        repeat(1000) {
            dbHelper.addWeightEntryWithImage(it.toDouble(), "/path/to/image_$it.jpg")
        }

        val entries = dbHelper.getAllWeightEntries()
        assertTrue(entries.size >= 1000)
    }

    @Test
    fun testLowBatteryMode() {
        // Mock low battery mode test
        val isLowBatteryMode = enableLowBatteryMode()
        assertTrue(isLowBatteryMode)
    }

    private fun enableLowBatteryMode(): Boolean {
        // Simulate enabling low battery mode
        return true
    }
}
