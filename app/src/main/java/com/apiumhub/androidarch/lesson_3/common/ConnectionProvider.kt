package com.apiumhub.androidarch.lesson_3.common

import kotlin.math.absoluteValue
import kotlin.random.Random

class ConnectionProvider {
    fun hasNetworkConnection() = Random.nextInt().absoluteValue % 100 > 10
}