package com.apiumhub.ui_testing.lesson_7

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.apiumhub.ui_testing.R
import org.junit.Rule
import org.junit.Test

class MyFirstComponentTest {

    @get:Rule
    var activityRule: ActivityTestRule<UIComponentsActivity> = ActivityTestRule(UIComponentsActivity::class.java)

    @Test
    fun shouldChangeButtonTextWhenClicked() {
        val follow = activityRule.activity.getString(R.string.follow)
        val following = activityRule.activity.getString(R.string.following)
        val button = onView(withId(R.id.firstComponentBtn))
        button.check(matches(withText(follow)))
        button.perform(click())
        button.check(matches(withText(following)))
    }

    @Test
    fun shouldChangeDescriptionTextWhenUsernameIsUpdated() {
        val username = "other username"
        val expectedDescription = String.format(activityRule.activity.getString(R.string.description), username)
        val component = activityRule.activity.findViewById<MyFirstComponent>(R.id.firstComponentInActivity)
        component.updateUsername(username)

        onView(withId(R.id.firstComponentUsername)).check(matches(withText(username)))
        onView(withId(R.id.firstComponentDescription)).check(matches(withText(expectedDescription)))

    }
}