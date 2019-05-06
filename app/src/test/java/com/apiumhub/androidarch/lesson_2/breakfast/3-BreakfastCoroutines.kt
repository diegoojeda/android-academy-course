package com.apiumhub.androidarch.lesson_2.breakfast

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Test

class `3-BreakfastCoroutines` {

    /*
    This way we start all tasks at once, but only await for them when we need their result.
    */

    @Test
    fun breakfastWithCoroutines() = runBlocking {

        val coffeeDeferred = async { pourCoffeeAsync() }
        val eggsDeferred = async { fryEggsAsync(2) }
        val toastDeferred = async { toastBreadAsync(2) }
        val baconDeferred = async { fryBaconAsync(3) }
        val ojDeferred = async { pourOrangeJuiceAsync() }

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
        val coffeeDeferred = async { pourCoffeeAsync() }
        val eggsDeferred = async { fryEggsAsync(2) }
        val toastDeferred = async { toastBreadAsync(2) }
        val baconDeferred = async { fryBaconAsync(3) }
        val ojDeferred = async { pourOrangeJuiceAsync() }

        awaitAll(coffeeDeferred, eggsDeferred, toastDeferred, baconDeferred, ojDeferred)
        println("coffee is ready")
        println("eggs are ready")
        println("bacon is ready")
        println("orange juice is ready")
        println("toast is ready")
        println("Breakfast is ready!")
    }

    private suspend fun pourCoffeeAsync(): Coffee {
        delay()
        return Coffee()
    }

    private suspend fun fryEggsAsync(amount: Int): Egg {
        delay(amount)
        return Egg()
    }

    private suspend fun fryBaconAsync(slices: Int): Bacon {
        delay(slices)
        return Bacon()
    }

    private suspend fun toastBreadAsync(slices: Int): Toast {
        delay(slices)
        val plainToast = Toast()
        applyButter(plainToast)
        applyJam(plainToast)
        return plainToast
    }

    private suspend fun applyButter(toast: Toast) {
        delay()
    }

    private suspend fun applyJam(toast: Toast) {
        delay()
    }

    private suspend fun pourOrangeJuiceAsync(): Juice {
        delay()
        return Juice()
    }
}