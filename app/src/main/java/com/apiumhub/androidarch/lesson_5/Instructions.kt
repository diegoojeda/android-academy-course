package com.apiumhub.androidarch.lesson_5

interface Instruction {
    fun apply(position: Position): Position
}

class RotateLeft : Instruction {
    override fun apply(position: Position): Position {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class RotateRight : Instruction {
    override fun apply(position: Position): Position {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class MoveForward : Instruction {

    private val moves = HashMap<Orientation, (Position) -> Position>()

    init {
        TODO("Fill moves map with position changing when moving on any orientation")
    }

    override fun apply(position: Position): Position {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
