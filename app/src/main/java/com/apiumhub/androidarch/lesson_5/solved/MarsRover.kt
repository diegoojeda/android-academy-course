package com.apiumhub.androidarch.lesson_5.solved

import java.util.*

class MarsRover {

    private val instructions = HashMap<Char, Instruction>()
    private val orientations = HashMap<Char, Orientation>()

    init {
        instructions['L'] = RotateLeft()
        instructions['R'] = RotateRight()
        instructions['M'] = MoveForward()
        orientations['N'] = Orientation.NORTH
        orientations['S'] = Orientation.SOUTH
        orientations['E'] = Orientation.EAST
        orientations['W'] = Orientation.WEST
    }

    fun compute(input: String): String {
        val (gridDimensionInput, initialPositionInput, pathInput) = input.split("\n")

        val (x, y, orientation) = initialPositionInput.split(" ")
        val initialPosition = Position(
            x.toInt(),
            y.toInt(),
            parseOrientation(orientation[0])
        )

        val path = Path(pathInput.toCharArray().map {
            parseInstruction(it)
        })
        val finalPosition = path.computeDestination(initialPosition)

        return "${finalPosition.x} ${finalPosition.y} ${toString(finalPosition.orientation)}"
    }

    private fun parseOrientation(input: Char): Orientation =
        orientations.getOrElse(input) { throw IllegalArgumentException("Unknown instruction $input") }

    private fun parseInstruction(input: Char): Instruction =
        instructions.getOrElse(input) { throw IllegalArgumentException("Unknown instruction $input") }

    private fun toString(orientation: Orientation): String =
        orientations.filterValues { it == orientation }.keys.first().toString()
}