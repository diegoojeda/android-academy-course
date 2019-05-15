package com.apiumhub.androidarch.lesson_5

data class Position(val x: Int, val y: Int, val orientation: Orientation)

enum class Orientation(private val left: () -> Orientation, private val right: () -> Orientation) {
    NORTH({ WEST }, { EAST }),
    SOUTH({ EAST }, { WEST }),
    EAST({ NORTH }, { SOUTH }),
    WEST({ SOUTH }, { NORTH });

    fun left(): Orientation = left.invoke()

    fun right(): Orientation = right.invoke()
}

