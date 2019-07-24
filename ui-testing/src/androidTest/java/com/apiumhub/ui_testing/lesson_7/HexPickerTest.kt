package com.apiumhub.ui_testing.lesson_7

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.apiumhub.ui_testing.R
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test

class HexPickerTest {
    @get:Rule
    var activityRule: ActivityTestRule<UIComponentsActivity> = ActivityTestRule(UIComponentsActivity::class.java)

    @Test
    fun should_load_hex_picker_with_green() {
        val expectedColor = activityRule.activity.getColor(R.color.colorAccent)
        val component = activityRule.activity.findViewById<HexPicker>(R.id.hexPickerInActivity)
        val actualColor: Int = component.getColor()

        assertEquals(expectedColor, actualColor)
    }

    @Test
    fun should_change_color_when_hex_color_is_typed() {
        val oldColor = (activityRule.activity.findViewById<View>(R.id.hexView).background as? ColorDrawable)?.color ?: 0
        onView(withId(R.id.hexEt)).perform(typeText("#000000"))
        val actualColor = (activityRule.activity.findViewById<View>(R.id.hexView).background as? ColorDrawable)?.color ?: 0

        assertNotEquals(oldColor, actualColor)
    }
}