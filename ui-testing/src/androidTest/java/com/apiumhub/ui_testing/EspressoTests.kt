package com.apiumhub.ui_testing

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class EspressoTests {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun shouldClickHomeNavButtonAndAssertText() {
        onView(withId(R.id.navigation_home)).perform(click())
        onView(withId(R.id.text_home)).check(matches(withText("This is home Fragment")))
    }

    @Test
    fun shouldClickDashboardNavButtonAndAssertText() {
        onView(withId(R.id.navigation_dashboard)).perform(click())
        onView(withId(R.id.text_dashboard)).check(matches(withText("This is dashboard Fragment")))
    }

    @Test
    fun shouldClickNotificationsNavButtonAndAssertText() {
        onView(withId(R.id.navigation_notifications)).perform(click())
        onView(withId(R.id.text_notifications)).check(matches(withText("This is notifications Fragment")))
    }
}