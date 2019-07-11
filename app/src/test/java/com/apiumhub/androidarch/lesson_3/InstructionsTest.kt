package com.apiumhub.androidarch.lesson_3

import org.junit.Assert
import org.junit.Test

class RotateLeftTest {

    @Test
    fun should_face_west_when_facing_north_and_rotate_left() {
        val startOrientation = Orientation.NORTH
        val expected = Orientation.WEST

        val finalOrientation = startOrientation.left()
        Assert.assertEquals(finalOrientation, expected)
    }

    @Test
    fun should_face_south_when_facing_west_and_rotate_left() {
        val startOrientation = Orientation.WEST
        val expected = Orientation.SOUTH

        val finalOrientation = startOrientation.left()
        Assert.assertEquals(finalOrientation, expected)
    }

    @Test
    fun should_face_north_when_facing_east_and_rotate_left() {
        val startOrientation = Orientation.EAST
        val expected = Orientation.NORTH

        val finalOrientation = startOrientation.left()
        Assert.assertEquals(finalOrientation, expected)
    }
}

class RotateRightTest {

    @Test
    fun should_face_west_when_facing_south_and_rotate_right() {
        val startOrientation = Orientation.SOUTH
        val expected = Orientation.WEST

        val finalOrientation = startOrientation.right()
        Assert.assertEquals(finalOrientation, expected)
    }

    @Test
    fun should_face_south_when_facing_east_and_rotate_right() {
        val startOrientation = Orientation.EAST
        val expected = Orientation.SOUTH

        val finalOrientation = startOrientation.right()
        Assert.assertEquals(finalOrientation, expected)
    }
}

class MoveForwardTest {

    @Test
    fun should_increment_y_when_facing_north_and_move_forward() {
        val initialPosition = Position(0,0, Orientation.NORTH)
        val expected = Position(0,1,Orientation.NORTH)

        val finalPosition =  MoveForward().apply(initialPosition)
        Assert.assertEquals(finalPosition, expected)
    }

    @Test
    fun should_increment_x_when_facing_east_and_move_forward() {
        val initialPosition = Position(0,0, Orientation.EAST)
        val expected = Position(1,0,Orientation.EAST)

        val finalPosition =  MoveForward().apply(initialPosition)
        Assert.assertEquals(finalPosition, expected)
    }

}

class MoveTorwardTest {

    @Test
    fun should_increment_y_when_facing_north_and_move_forward() {
        //TODO()
    }

    //TODO Missing other orientations
}
