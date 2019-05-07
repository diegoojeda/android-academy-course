package com.apiumhub.androidarch.lesson_2.breakfast

import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.IOException

class `5-AsyncUI` {

    class AsyncUiWithListeners {
        private val textView = SomeTextView()

        @Test
        fun runOnUiThread() {
            makeAsyncRequest { result ->
                try {
                    runOnUiThread {
                        textView.text = result
                    }
                } catch (exception: IOException) {
                    println("Failed")
                }
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

    class AsyncUICoroutines {

        private val textView = SomeTextView()

        @Test
        fun asyncUIWithCoroutines() = runBlocking(/*Dispatchers.Main*/) {
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

    /*
    Dummy class that represents an Android TextView
     */
    class SomeTextView {
        var text = ""
    }
}