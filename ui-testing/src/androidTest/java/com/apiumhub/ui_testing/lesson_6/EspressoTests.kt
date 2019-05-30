package com.apiumhub.ui_testing.lesson_6

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.apiumhub.ui_testing.R
import com.apiumhub.ui_testing.lesson_6.ui.LoggedInActivity
import org.hamcrest.CoreMatchers.not
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

    //region solutions

    @Test
    fun navigateToLoginTypeInvalidEmailAndCheckButtonIsDisabled() {
        navigateToLogin()
        onView(withId(R.id.login_username_et)).perform(replaceText("invalid email"))
        assertSubmitButtonIsDisabled()
    }

    @Test
    fun navigateToLoginTypeInvalidPasswordAndCheckButtonIsDisabled() {
        navigateToLogin()
        onView(withId(R.id.login_password_et)).perform(replaceText("1234"))
        assertSubmitButtonIsDisabled()
    }

    @Test
    fun navigateToLoginTypeValidCredentialsAndNavigateToNextScreen() {
        navigateToLogin()
        onView(withId(R.id.login_username_et)).perform(replaceText("someEmail@someDomain.com"))
        onView(withId(R.id.login_password_et)).perform(replaceText("123456"))
        onView(withId(R.id.login_submit_btn)).perform(click())
        waitUntilActivityVisible<LoggedInActivity>()
        onView(withId(R.id.logged_in_tv)).check(matches(withText("User someEmail@someDomain.com is logged in")))
    }

    private fun assertSubmitButtonIsDisabled() {
        onView(withId(R.id.login_submit_btn)).check(matches(not(isEnabled())))
    }

    private fun navigateToLogin() {
        onView(withId(R.id.navigation_login)).perform(click())
    }

    //endregion
}