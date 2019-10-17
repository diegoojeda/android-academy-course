package com.apiumhub.androidarch.lesson_8

import org.junit.Assert.assertEquals
import org.junit.Test

class SquareRectTest {

    @Test
    fun `rectangle area works`(){
        val forma: Rectangle = Rectangle(2, 3)
        assertEquals(6, forma.getArea())

        val forma2: Rectangle = Square(2)
        assertEquals(4, forma2.getArea())
    }
}

open class Rectangle(open val height: Int, open val width: Int) {
    open fun getArea() = height * width
}

class Square(side: Int): Rectangle(side, side)