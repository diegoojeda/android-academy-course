package com.apiumhub.androidarch.lesson_3

import org.junit.Assert.assertEquals
import org.junit.Test

class PositionTestShould {

    @Test
    fun moveLeft() {
        val startOrientation = Orientation.NORTH
        val expected = Orientation.WEST

        val finalOrientation = startOrientation.left()
        assertEquals(finalOrientation, expected)
    }
}