package com.example.weightogo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HistoryPageUITest {

    @Test
    fun testHistoryPageDisplaysEntries() {
        // Navigate to the history page
        onView(withId(R.id.historyNav)).perform(click())

        // Check if the history list view is displayed
        onView(withId(R.id.weightListView)).check(matches(isDisplayed()))
    }
}
