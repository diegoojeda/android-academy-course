package com.apiumhub.androidarch.lesson_5.solved

import java.util.*

interface Instruction {
    fun apply(position: Position): Position
}

class RotateLeft : Instruction {
    override fun apply(position: Position): Position {
        return Position(
            position.x,
            position.y,
            position.orientation.left()
        )
    }
}

class MoveForward : Instruction {

    private val moves = HashMap<Orientation, (Position) -> Position>()

    init {
        moves[Orientation.NORTH] = {
            Position(
                it.x,
                it.y.plus(1),
                it.orientation
            )
        }
        moves[Orientation.EAST] = {
            Position(
                it.x.plus(1),
                it.y,
                it.orientation
            )
        }
        moves[Orientation.SOUTH] = {
            Position(
                it.x,
                it.y.minus(1),
                it.orientation
            )
        }
        moves[Orientation.WEST] = {
            Position(
                it.x.minus(1),
                it.y,
                it.orientation
            )
        }
    }

    override fun apply(position: Position): Position {
        return moves[position.orientation]!!.invoke(position)
    }
}

class RotateRight : Instruction {
    override fun apply(position: Position): Position {
        return Position(
            position.x,
            position.y,
            position.orientation.right()
        )
    }
}


