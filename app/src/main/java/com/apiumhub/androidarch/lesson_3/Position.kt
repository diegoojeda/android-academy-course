package com.apiumhub.androidarch.lesson_3

data class Position(val x: Int, val y: Int, val orientation: Orientation)

enum class Orientation {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    fun left(): Orientation{
        return when(this){
            WEST -> SOUTH
            EAST -> NORTH
            NORTH -> WEST
            SOUTH -> EAST
        }
    }
    fun right(): Orientation {
        return when(this){
            WEST -> NORTH
            EAST -> SOUTH
            NORTH -> EAST
            SOUTH -> WEST
        }
    }
}
