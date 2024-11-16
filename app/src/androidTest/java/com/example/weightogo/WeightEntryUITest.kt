package com.example.weightogo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeightEntryUITest {

    @Test
    fun testWeightEntryScreenUI() {
        // Check if weight input field is displayed
        onView(withId(R.id.input_weight)).check(matches(isDisplayed()))

        // Check if take picture button is displayed
        onView(withId(R.id.btn_take_picture)).check(matches(isDisplayed()))

        // Check if save button is displayed
        onView(withId(R.id.btn_save_weight)).check(matches(isDisplayed()))
    }
}
