package com.apiumhub.androidarch.lesson_2.breakfast

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class `3-TODOBreakfastCoroutines` {

    //TODO Implement the same breakfast example but using coroutines

    @Test
    fun `breakfast coroutines sequentially`() = runBlocking {
        Unit
    }

    @Test
    fun `breakfast sequentially asynchronous`() = runBlocking {
        Unit
    }

    @Test
    fun `breakfast async awaiting`() = runBlocking {
        Unit
    }

    //region common
    private suspend fun pourCoffee(): Coffee {
        delay(10)
        return Coffee()
    }

    private suspend fun fryEggs(amount: Int): Egg {
        delay(amount)
        return Egg()
    }

    private suspend fun fryBacon(slices: Int): Bacon {
        delay(slices)
        return Bacon()
    }

    private suspend fun toastBread(slices: Int): Toast {
        delay(slices)
        return Toast()
    }

    private suspend fun applyButter(toast: Toast): ToastWithButter {
        delay()
        return ToastWithButter()
    }

    private suspend fun applyJam(toast: ToastWithButter): ToastWithJamAndButter {
        delay()
        return ToastWithJamAndButter()
    }

    private suspend fun pourOrangeJuice(): Juice {
        delay()
        return Juice()
    }
    //endregion

    //region solution
    //region 1 - breakfast preparing synchronously with suspend functions
    /*
    suspend functions can be executed as regular synchronous functions. The function they're called
    within must be a suspend function, and as such it can be suspended.
    In this case our test is not a suspend function, but executes a runBlocking code block; this means
    that the the execution of every coroutine launched inside the runBlocking block will be finished.
    This won't happen if, for example, we start our coroutines with the "launch { }" construct
     */
    @Test
    fun breakfastNotAwaiting() = runBlocking {
        //This execution takes ~18 seconds
        pourCoffee()
        println("coffee is ready")
        fryEggs(2)
        toastBread(2)
        fryBacon(3)
        pourOrangeJuice()
        println("Breakfast is ready!")
        Unit
    }

    @Test
    fun breakfastNotAwaitingWithLaunch() {
        //This execution does not wait until the coroutines are finished
        GlobalScope.launch {
            pourCoffee()
            println("coffee is ready")
            fryEggs(2)
            toastBread(2)
            fryBacon(3)
            pourOrangeJuice()
            println("Breakfast is ready!")
        }
    }
    //endregion

    //region 2 - breakfast preparing asynchronously with the same suspend functions
    /*
    This way we start all tasks at once, but only await for them when we need their result.
    */

    @Test
    fun breakfastWithCoroutines() = runBlocking {

        val coffeeDeferred = async { pourCoffee() }
        val eggsDeferred = async { fryEggs(2) }
        val toastDeferred = async { toastBread(2) }
        val baconDeferred = async { fryBacon(3) }
        val ojDeferred = async { pourOrangeJuice() }

        coffeeDeferred.await()
        println("coffee is ready")
        eggsDeferred.await()
        println("eggs are ready")
        baconDeferred.await()
        println("bacon is ready")
        ojDeferred.await()
        println("orange juice is ready")
        toastDeferred.await()
        println("toast is ready")
        println("Breakfast is ready!")
    }

    @Test
    fun breakfastWithAwaitAll() = runBlocking {
        val coffeeDeferred = async { pourCoffee() }
        val eggsDeferred = async { fryEggs(2) }
        val toastDeferred = async { toastBread(2) }
        val baconDeferred = async { fryBacon(3) }
        val ojDeferred = async { pourOrangeJuice() }

        Common.awaitAll(coffeeDeferred, eggsDeferred, toastDeferred, baconDeferred, ojDeferred)
        println("coffee is ready")
        println("eggs are ready")
        println("bacon is ready")
        println("orange juice is ready")
        println("toast is ready")
        println("Breakfast is ready!")
    }
    //endregion


    //endregion

}