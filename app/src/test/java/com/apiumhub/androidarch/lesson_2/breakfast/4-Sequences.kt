package com.apiumhub.androidarch.lesson_2.breakfast

import org.junit.Test

class `4-Sequences` {

    /*
    As .yield(n) is a suspend function, even if it's wrapped inside a while(true) loop, it can be suspended so the sequence
    only provides the .take(n) items requested
     */
    @Test
    fun fibonacciSequence() {
        val fibonacci: Sequence<Int> = sequence {
            yield(1) // first Fibonacci number
            var cur = 1
            var next = 1
            while (true) {
                yield(next) // next Fibonacci number
                val tmp = cur + next
                cur = next
                next = tmp
            }
        }
        println(fibonacci.take(10).joinToString())
    }
}