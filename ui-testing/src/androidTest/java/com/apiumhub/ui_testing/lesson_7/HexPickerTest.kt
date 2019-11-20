package com.apiumhub.ui_testing.lesson_7

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasBackground
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.apiumhub.ui_testing.R
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

class HexPickerTest {
    @get:Rule
    var activityRule: ActivityTestRule<UIComponentsActivity> = ActivityTestRule(UIComponentsActivity::class.java)

    @Test
    fun should_load_hex_picker_with_green() {

    }

    @Test
    fun should_change_color_when_hex_color_is_typed() {
        val newColor = "#123456"
        val expected = Color.parseColor(newColor)
        val hexView = activityRule.activity.findViewById<View>(R.id.hexView)

        onView(withId(R.id.hexEt)).perform(typeText(newColor))

        val actual = (hexView.background as? ColorDrawable)?.color ?: 0
        assertEquals(expected, actual)
    }
}