package com.example.weightogo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AccessibilityTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAccessibilityLabels() {
        // Check that all buttons have content descriptions
        onView(withId(R.id.btn_take_picture)).check(matches(withContentDescription()))
        onView(withId(R.id.btn_save_weight)).check(matches(withContentDescription()))
    }

    @Test
    fun testNavigationToHistoryPage() {
        onView(withId(R.id.historyNav)).perform(click())
        onView(withId(R.id.weightListView)).check(matches(isDisplayed()))
    }

    @Test
    fun testBackButtonNavigation() {
        onView(withId(R.id.historyNav)).perform(click())
        onView(withId(R.id.backButton)).perform(click())
        onView(withId(R.id.dashboardView)).check(matches(isDisplayed()))
    }

    @Test
    fun testAccessibilityLabels() {
        onView(withId(R.id.btn_take_picture)).check(matches(withContentDescription("Take Picture Button")))
        onView(withId(R.id.btn_save_weight)).check(matches(withContentDescription("Save Weight Button")))
    }

    @Test
    fun testKeyboardNavigation() {
        // Ensure focus moves logically when using Tab key
        // Requires Espresso support for keyboard navigation (setup additional libraries if needed)
        onView(withId(R.id.input_weight)).check(matches(isFocusable()))
        onView(withId(R.id.btn_take_picture)).check(matches(isFocusable()))
    }
}
