package com.apiumhub.androidarch.lesson_3.solved

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MarsRoverTest {

    @Test
    fun should_compute_final_position_when_initial_position_and_path_provided() {
        val marsRover = MarsRover()

        val finalPosition = marsRover.compute("1 2 N\nLMLMLMLMM")

        assertThat(finalPosition).isEqualTo("1 3 N")
    }
}