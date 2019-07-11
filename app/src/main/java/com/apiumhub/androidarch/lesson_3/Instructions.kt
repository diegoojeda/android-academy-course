package com.apiumhub.androidarch.lesson_3

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
        moves[Orientation.NORTH] = {
            it.copy(y = it.y+1)
        }
        moves[Orientation.SOUTH] = {
            it.copy(y = it.y-1)
        }
        moves[Orientation.EAST] = {
            it.copy(x = it.x + 1)
        }
        moves[Orientation.WEST] = {
            it.copy(x = it.x-1)
        }
    }

    override fun apply(position: Position): Position {
        val function = moves.get(position.orientation)!!
        return function(position)
    }
}
