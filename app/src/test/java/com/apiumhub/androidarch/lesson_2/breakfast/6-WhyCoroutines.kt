package com.apiumhub.androidarch.lesson_2.breakfast

import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.FileInputStream
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread

class `6-WhyCoroutines` {

    /*
    In this case, we might not know what does "getFileSizeWithCoroutines" does, we can close the FileInputStream right after the function
    call returns its execution, but this will lead to an exception as the stream is processed on a new thread.
     */
    @Test
    fun fileProcessingWithThreadsAndCallbacks() {
        val countDownLatch = CountDownLatch(1)
        val fos = FileInputStream("1-BreakfastSequentially.kt")
        getFileSizeInBackground(fos) {
            println("Finished processing file")
            println("Read $it bytes")
            countDownLatch.countDown()
        }
        fos.close()
        countDownLatch.await()
    }

    private fun getFileSizeInBackground(file: FileInputStream, callback: (Int) -> Unit) {
        thread(start = true) {
            sleep(1)
            callback(file.read())
        }
    }

    /*
    By using coroutines, we can make the whole code look sequential. But not only this, the point in this example is that,
    by the time we close the FileOutputStream, we are 100% sure that the whole processing inside "getFileSizeWithCoroutines"
    has finished
     */
    @Test
    fun fileProcessingWithCoroutines() = runBlocking {
        val fos = FileInputStream("1-BreakfastSequentially.kt")
        val bytes = getFileSizeWithCoroutines(fos)
        fos.close()
        println("Finished processing file")
        println("Read $bytes bytes")
    }

    private suspend fun getFileSizeWithCoroutines(file: FileInputStream): Int {
        delay(1)
        return file.read()
    }
}