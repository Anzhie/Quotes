package com.khasanova.quotes.presentation

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.khasanova.quotes.R
import com.khasanova.quotes.presentation.quotes.QuotesFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val TEST_KEY = "testKey123"

@RunWith(AndroidJUnit4::class)
class QuotesFragmentTest {

    private lateinit var scenario: FragmentScenario<QuotesFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(bundleOf(), R.style.Theme_MaterialComponents_DayNight)
    }

    @Test
    fun dialogDisplayedWhenStart() {
        onView(withId(R.id.textViewSettings)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextRapidKey)).check(matches(isDisplayed()))
    }

    @Test
    fun dialogChangeText() {
        onView(withId(R.id.editTextRapidKey)).perform(typeText(TEST_KEY), closeSoftKeyboard())

        onView(withId(R.id.editTextRapidKey)).check(matches(withText(TEST_KEY)))
    }

    @Test
    fun errorStateWhenNoKey() {
        onView(withId(android.R.id.button1)).perform(click())

        onView(withId(R.id.textViewError)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonSettings)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonError)).check(matches(isDisplayed()))
    }
}