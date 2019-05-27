package com.apiumhub.androidarch.lesson_2

import org.junit.Assert
import org.junit.Test

class `4-Sequences` {

    /*
    As .yield(n) is a suspend function, even if it's wrapped inside a while(true) loop, it can be suspended so the sequence
    only provides the .take(n) items requested
     */
    @Test
    fun fibonacciSequence() {
        val fibonacci: Sequence<Int> = sequence {
            var current = 1
            var next = 1
            while (true) {
                yield(current) // next Fibonacci number
                val tmp = current + next
                current = next
                next = tmp
            }
        }
        val actual = fibonacci.take(10).joinToString(separator = ", ")
        println(actual)
        Assert.assertEquals("1, 1, 2, 3, 5, 8, 13, 21, 34, 55", actual)
    }
}