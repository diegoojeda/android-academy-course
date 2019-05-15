package com.apiumhub.androidarch.lesson_5

data class Path(private val instructions: List<Instruction>) {

    fun computeDestination(initialPosition: Position): Position {
        return instructions.fold(initialPosition) { acc, instruction -> instruction.apply(acc) }
    }

}