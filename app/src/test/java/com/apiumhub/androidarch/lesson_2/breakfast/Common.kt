package com.apiumhub.androidarch.lesson_2.breakfast

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay

class Coffee
class Egg
class Bacon
class Juice
class Toast

fun sleep(seconds: Int = 1) {
//    println("Running on ${Thread.currentThread().name}")
    Thread.sleep(seconds * 1000L)
}

suspend fun delay(times: Int = 1) {
    for (i in 0..times) {
        delay(1000)
    }
}

object Common{
    suspend fun awaitAll(vararg coroutines: Deferred<*>) {
        coroutines.forEach { it.await() }
    }
}
