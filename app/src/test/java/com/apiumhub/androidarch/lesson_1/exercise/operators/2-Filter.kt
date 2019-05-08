package com.apiumhub.androidarch.lesson_1.exercise.operators

import org.junit.Assert
import org.junit.Test

/*
Implement the method filter, which accepts a linked list (head) and a predicate function,
and returns a new linked list (head) which only contains the elements which apply to the given predicate.

For example: Given the list: 1 -> 2 -> 3, and the predicate x => x >= 2, filter should return 2 -> 3, since x >= 2 applies to both 2 and 3.
 */

fun <INPUT> filterRecursive(list: List<INPUT>, predicate: (INPUT) -> Boolean): List<INPUT> {
    if (list.size == 1) {
        return if (predicate(list.first())) listOf(list.first()) else emptyList()
    }
    return if (predicate(list.first())) {
        listOf(list.first()).plus(filterRecursive(list.subList(1, list.size), predicate))
    } else {
        filterRecursive(list.subList(1, list.size), predicate)
    }
}

fun <INPUT> filterIterative(list: List<INPUT>, predicate: (INPUT) -> Boolean): List<INPUT> {
    val out = mutableListOf<INPUT>()
    for (item in list) {
        if (predicate(item)) {
            out.add(item)
        }
    }
    return out
}

class FilterTest {
    @Test
    fun testFilterRecursive() {
        val list = listOf(1, 2, 3, 4)
        val expected = listOf(2, 4)
        val isEven = { p1: Int -> p1 % 2 == 0 }
        val actual = filterRecursive(list, isEven)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testFilterIterative() {
        val list = listOf(1, 2, 3, 4)
        val expected = listOf(2, 4)
        val isEven = { p1: Int -> p1 % 2 == 0 }
        val actual = filterIterative(list, isEven)
        Assert.assertEquals(expected, actual)
    }
}