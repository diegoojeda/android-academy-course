package com.apiumhub.androidarch.lesson_1.exercise.operators

import org.junit.Assert
import org.junit.Test

/*
Implement the method reduce, which accepts three arguments:

linked list (head)
bi-function - (accumulated_value, current_element_data)
initial value
This method should return the result of applying the given function on every element with the accumulating result, starting with the initial value.

For example:

Given the list: 1 -> 2 -> 3, the function (acc, curr) => acc + curr and an initial value of 0, reduce should return 6, because:

(0, 1) and the function (acc, curr) => acc + curr gives 1
(1, 2) and the function (acc, curr) => acc + curr gives 3
(3, 3) and the function (acc, curr) => acc + curr gives 6
Another example:

Given the list: 1 -> 2 -> 3 -> 4, the function (acc, curr) => acc * curr and an initial value of 1, reduce should return 24
 */

fun <INPUT, OUTPUT> reduce(
    list: List<INPUT>,
    initialValue: OUTPUT,
    operation: (acc: OUTPUT, INPUT) -> OUTPUT
): OUTPUT = TODO()

//region solutions
fun <INPUT, OUTPUT> reduceRecursive(
        list: List<INPUT>,
        initialValue: OUTPUT,
        operation: (acc: OUTPUT, INPUT) -> OUTPUT
): OUTPUT {
    if (list.size == 1) {
        return operation(initialValue, list.first())
    }
    return reduceRecursive(list.subList(1, list.size), operation(initialValue, list.first()), operation)
}

fun <INPUT, OUTPUT> reduceIterative(
        list: List<INPUT>,
        initialValue: OUTPUT,
        operation: (acc: OUTPUT, INPUT) -> OUTPUT
): OUTPUT {
    var value = initialValue
    for (item in list) {
        value = operation(value, item)
    }
    return value
}

//endregion

class ReduceTest {
    @Test
    fun testReduceRecursive() {
        val list = listOf(1, 2, 3)
        val operation = { acc: Int, next: Int -> acc + next }
        val expected = 6
        val actual = reduceRecursive(list, 0, operation)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testReduceIterative() {
        val list = listOf(1, 2, 3)
        val operation = { acc: Int, next: Int -> acc + next }
        val expected = 6
        val actual = reduceIterative(list, 0, operation)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testReduce() {
        val list = listOf(1, 2, 3)
        val operation = { acc: Int, next: Int -> acc + next }
        val expected = 6
        val actual = reduce(list, 0, operation)
        Assert.assertEquals(expected, actual)
    }
}