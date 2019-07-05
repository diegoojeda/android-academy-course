package com.apiumhub.androidarch.lesson_2

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.concurrent.thread

class CoroutinesVsThreads {



    @Test
    fun test() = runBlocking {
        launch { // -> Coroutine scope
            delay(1000) // -> Suspend execution for 1 second
            println("World!")
        }
        println("Hello ")
    }




    @Test
    fun generateThreads() {
        repeat(100_000) {
            thread(start = true) {
                Thread.sleep(1000L)
                print(".")
            }
        }
    }

    @Test
    fun generateCoroutines() = runBlocking {
        repeat(100_000) {
            launch {
                delay(1000L)
                print(".")
            }
        }
    }
}