package com.apiumhub.androidarch.lesson_1.exercise.operators

import org.junit.Assert
import org.junit.Test

/*
Implement the method map, which accepts a list and a mapping function,
and returns a new list where every element is the result of applying the
 given mapping method to each element of the original list.

For example: Given the list: 1 -> 2 -> 3, and the mapping function x => x * 2, map should return 2 -> 4 -> 6
 */

fun <INPUT, OUTPUT> mapRecursive(list: List<INPUT>, transform: (INPUT) -> OUTPUT): List<OUTPUT> {
    if (list.size == 1) {
        return listOf(transform(list.first()))
    }
    return listOf(transform(list.first())).plus(mapRecursive(list.subList(1, list.size), transform))
}

fun <INPUT, OUTPUT> mapIterative(list: List<INPUT>, transform: (INPUT) -> OUTPUT): List<OUTPUT> {
    val out = mutableListOf<OUTPUT>()
    for (item in list) {
        out.add(transform(item))
    }
    return out
}

class MapTest {

    @Test
    fun testMapRecursive() {
        val list = listOf(1, 2, 3, 4, 5)
        val expected = listOf(2, 4, 6, 8, 10)
        val actual = mapRecursive(list) { it * 2 }
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testMapIterative() {
        val list = listOf(1, 2, 3, 4, 5)
        val expected = listOf(2, 4, 6, 8, 10)
        val actual = mapIterative(list) { it * 2 }
        Assert.assertEquals(expected, actual)
    }
}