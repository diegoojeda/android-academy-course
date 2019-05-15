package com.apiumhub.androidarch.lesson_5.solved

data class Path(val instructions: List<Instruction>) {

    fun computeDestination(initialPosition: Position): Position =
        instructions.fold(initialPosition) { acc, next -> next.apply(acc) }

}

