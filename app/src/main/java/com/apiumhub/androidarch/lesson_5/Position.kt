package com.apiumhub.androidarch.lesson_5

data class Position(val x: Int, val y: Int, val orientation: Orientation)

enum class Orientation {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    fun left(): Orientation = TODO()
    fun right(): Orientation = TODO()
}
