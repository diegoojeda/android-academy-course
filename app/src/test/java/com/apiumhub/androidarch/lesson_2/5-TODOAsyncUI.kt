package com.apiumhub.androidarch.lesson_2

import com.apiumhub.androidarch.lesson_2.breakfast.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.IOException

class `5-TODOAsyncUI` {

    class AsyncUiWithListeners {
        private val textView = SomeTextView()

        @Test
        fun runOnUiThread() {
            try {
                makeAsyncRequest { result ->
                    runOnUiThread {
                        textView.text = result
                    }
                }
            } catch (exception: IOException) {
                println("Failed")
            }
        }

        @Throws(IOException::class)
        private fun makeAsyncRequest(listener: (result: String) -> Unit) {
            Thread.sleep(1000)
            //Maybe throw some exception here if needed.
            listener("Some result")
        }

        private fun runOnUiThread(func: () -> Unit) {
            func()
        }
    }

    class Coroutines {
        private val textView = SomeTextView()

        @Test
        fun runOnUiThread() = runBlocking(Dispatchers.Main) {
            try {
                val result = makeAsyncRequest()
                textView.text = result
                println("Finished")
            } catch (e: IOException) {
                println("Error")
            }
            Unit
        }


        @Throws(IOException::class)
        private suspend fun makeAsyncRequest(): String {
            delay(1)
            return "Hello world!"
        }

        private fun runOnUiThread(func: () -> Unit) {
            func()
        }
    }

    //region solution
    class AsyncUICoroutines {

        private val textView = SomeTextView()

        @Test
        fun asyncUIWithCoroutines() = runBlocking/*launch(Dispatchers.Main)*/ {
            try {
                val result = makeAsyncRequest()
                /*
                We don't need runOnUiThread here. We're using Dispatchers.Main as the coroutine context.
                (Well, we would if this was production code. Dispatchers.main can't be used within tests)
                This means that the coroutine will run on the main thread, and won't block it while performing makeAsyncRequest, as it's a suspend function
                 */
                textView.text = result
            } catch (exception: IOException) {
                println("Failed")
            }
        }

        @Throws(IOException::class)
        private suspend fun makeAsyncRequest(): String {
            delay()
            //Maybe throw some exception here if needed.
            return "Some result"
        }
    }

//endregion

    /*
    Dummy class that represents an Android TextView
     */
    class SomeTextView {
        var text = ""
    }
}