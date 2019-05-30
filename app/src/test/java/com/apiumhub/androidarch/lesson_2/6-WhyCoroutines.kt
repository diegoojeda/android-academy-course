package com.apiumhub.androidarch.lesson_2

import com.apiumhub.androidarch.lesson_2.breakfast.delay
import com.apiumhub.androidarch.lesson_2.breakfast.sleep
import junit.framework.TestCase.fail
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.FileInputStream
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class `6-WhyCoroutines` {

    /*
    In this case, we might not know what does "getFileSizeWithCoroutines" does, we can close the FileInputStream right after the function
    call returns its execution, but this will lead to an exception as the stream is processed on a new thread.
     */
    @Test
    fun fileProcessingWithThreadsAndCallbacks() {
        val countDownLatch = CountDownLatch(1)
        val fis = FileInputStream(FILE_NAME)
        getFileSizeInBackground(fis) {
            println("Finished processing file")
            println("Read $it bytes")
            countDownLatch.countDown()
        }
        fis.close()
        countDownLatch.await(3, TimeUnit.SECONDS)
        if (hasNotEntered(countDownLatch)) {
            fail()
        }
    }

    private fun hasNotEntered(countDownLatch: CountDownLatch) =
        countDownLatch.count != 0L

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
        val fis = FileInputStream(FILE_NAME)
        val bytes = getFileSizeWithCoroutines(fis)
        fis.close()
        println("Finished processing file")
        println("Read $bytes bytes")
    }

    private suspend fun getFileSizeWithCoroutines(file: FileInputStream): Int {
        delay(1)
        return file.read()
    }

    @Test
    fun fileProcessingWithCoroutinesAndTryWithResources() = runBlocking {
        FileInputStream(FILE_NAME).use {
            val bytes = getFileSizeWithCoroutines(it)
            println("Finished processing file")
            println("Read $bytes bytes")
        }
    }

    companion object {
        const val FILE_NAME = "example.txt"
    }
}