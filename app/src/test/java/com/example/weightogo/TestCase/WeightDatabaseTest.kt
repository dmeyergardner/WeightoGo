package com.example.weightogo.TestCase

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeightDatabaseTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val dbHelper = WeightDatabaseHelper(context)

    @Test
    fun testSaveWeightEntry() {
        // Insert weight entry
        val result = dbHelper.addWeightEntryWithImage(
            weight = 180.5,
            imagePath = "/path/to/test/image.jpg"
        )

        // Verify insertion was successful
        assert(result > 0)
    }

    @Test
    fun testRetrieveWeightEntries() {
        // Insert a test entry
        dbHelper.addWeightEntryWithImage(
            weight = 180.5,
            imagePath = "/path/to/test/image.jpg"
        )

        // Retrieve all entries
        val entries = dbHelper.getAllWeightEntries()

        // Check if at least one entry is retrieved
        assert(entries.isNotEmpty())

        // Check the contents of the retrieved entry
        val firstEntry = entries.first()
        assertEquals(180.5, firstEntry.weight, 0.0)
        assertEquals("/path/to/test/image.jpg", firstEntry.imagePath)
    }

    @Test
    fun testDeleteWeightEntry() {
        // Insert a test entry
        val id = dbHelper.addWeightEntryWithImage(
            weight = 180.5,
            imagePath = "/path/to/test/image.jpg"
        ).toInt()

        // Delete the entry
        val result = dbHelper.deleteWeightEntry(id)

        // Verify deletion
        assertEquals(1, result)
    }

    @Test
    fun testDataRetentionAfterRestart() {
        dbHelper.addWeightEntryWithImage(180.5, "/path/to/test/image.jpg")
        val entriesBeforeRestart = dbHelper.getAllWeightEntries()
        assert(entriesBeforeRestart.isNotEmpty())

        // Simulate app restart by re-initializing the database
        val newDbHelper = WeightDatabaseHelper(context)
        val entriesAfterRestart = newDbHelper.getAllWeightEntries()
        assertEquals(entriesBeforeRestart.size, entriesAfterRestart.size)
    }

}
