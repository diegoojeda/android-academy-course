package com.apiumhub.ui_testing.lesson_7

import androidx.test.rule.ActivityTestRule
import com.apiumhub.ui_testing.R
import junit.framework.TestCase.assertEquals
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
}