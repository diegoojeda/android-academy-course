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
        val followButton = onView(withId(R.id.firstComponentBtn))
        followButton.perform(click())
        followButton.check(matches(withText("Unfollow")))
        followButton.perform(click())
        followButton.check(matches(withText("Follow")))
    }

    @Test
    fun shouldChangeDescriptionTextWhenUsernameIsUpdated() {
        val userNameTv = onView(withId(R.id.firstComponentUsername))
        val descriptionTv = onView(withId(R.id.firstComponentDescription))

        val component = activityRule.activity.findViewById<MyFirstComponent>(R.id.firstComponentInActivity)

        component.username = "Other"

        userNameTv.check(matches(withText("Other")))
        descriptionTv.check(matches(withText("Description for Other")))

    }
}