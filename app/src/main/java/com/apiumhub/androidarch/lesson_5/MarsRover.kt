package com.apiumhub.androidarch.lesson_5

import com.apiumhub.androidarch.lesson_5.solved.Instruction
import com.apiumhub.androidarch.lesson_5.solved.MoveForward
import com.apiumhub.androidarch.lesson_5.solved.Orientation
import com.apiumhub.androidarch.lesson_5.solved.RotateLeft
import com.apiumhub.androidarch.lesson_5.solved.RotateRight
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
        TODO()
    }

    private fun parseOrientation(input: Char): Orientation =
        orientations.getOrElse(input) { throw IllegalArgumentException("Unknown instruction $input") }

    private fun parseInstruction(input: Char): Instruction =
        instructions.getOrElse(input) { throw IllegalArgumentException("Unknown instruction $input") }

    private fun toString(orientation: Orientation): String =
        orientations.filterValues { it == orientation }.keys.first().toString()
}