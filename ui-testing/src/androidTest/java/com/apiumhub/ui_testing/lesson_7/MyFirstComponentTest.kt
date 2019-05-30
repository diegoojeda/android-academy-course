package com.apiumhub.ui_testing.lesson_7

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.apiumhub.ui_testing.R
import org.junit.Rule
import org.junit.Test

class MyFirstComponentTest {

    @get:Rule
    var activityRule: ActivityTestRule<UIComponentsActivity> = ActivityTestRule(UIComponentsActivity::class.java)

    @Test
    fun shouldChangeButtonTextWhenClicked() {
        val button = onView(withId(R.id.firstComponentBtn))
        TODO()
    }

    @Test
    fun shouldChangeDescriptionTextWhenUsernameIsUpdated() {
        val component = activityRule.activity.findViewById<MyFirstComponent>(R.id.firstComponentInActivity)
        TODO()
    }
}