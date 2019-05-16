package com.apiumhub.androidarch.lesson_3

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PathTest {

    @Test
    fun should_apply_all_instructions_when_compute_destination() {
        // given
        val initialPosition = Position(
            1,
            2,
            Orientation.NORTH
        )
        val finalPosition = Position(
            0,
            2,
            Orientation.WEST
        )

        val path = Path(
            listOf(
                RotateLeft(),
                MoveForward()
            )
        )

        // when
        val destination = path.computeDestination(initialPosition)

        // then
        assertThat(destination).isEqualTo(finalPosition)
    }
}