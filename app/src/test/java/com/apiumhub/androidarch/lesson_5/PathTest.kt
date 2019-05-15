package com.apiumhub.androidarch.lesson_5

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.Arrays.asList

class PathTest {

    @Test
    fun should_apply_all_instructions_when_compute_destination() {
        // given
        val initialPosition = Position(1, 2, Orientation.NORTH)
        val intermediatePosition = Position(1, 2, Orientation.WEST)
        val finalPosition = Position(0, 2, Orientation.WEST)

        val path = Path(
            asList(
                FakeInstruction(initialPosition, intermediatePosition),
                FakeInstruction(intermediatePosition, finalPosition)
            )
        )

        // when
        val destination = path.computeDestination(initialPosition)

        // then
        assertThat(destination).isEqualTo(finalPosition)
    }
}

class FakeInstruction(val expected: Position, val result: Position) : Instruction {

    override fun apply(position: Position): Position {
        if (position.equals(expected)) {
            return result
        }
        throw RuntimeException("Expected: $expected, Actual: $position")
    }
}
