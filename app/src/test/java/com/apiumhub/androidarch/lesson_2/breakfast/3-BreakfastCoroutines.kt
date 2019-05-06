package com.apiumhub.androidarch.lesson_2.breakfast

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Test

class `3-BreakfastCoroutines` {

    /*
    This way we start all tasks at once, but only await for them when we need their result.
     */

    @Test
    fun breakfastWithCoroutines() = runBlocking {
        val coffeeDeferred = pourCoffeeAsync(this)
        val eggsDeferred = fryEggsAsync(this, 2)
        val toastDeferred = toastBreadAsync(this, 2)
        val baconDeferred = fryBaconAsync(this, 3)
        val ojDeferred = pourOrangeJuiceAsync(this)

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
        val coffeeDeferred = pourCoffeeAsync(this)
        val eggsDeferred = fryEggsAsync(this, 2)
        val toastDeferred = toastBreadAsync(this, 2)
        val baconDeferred = fryBaconAsync(this, 3)
        val ojDeferred = pourOrangeJuiceAsync(this)

        awaitAll(coffeeDeferred, eggsDeferred, toastDeferred, baconDeferred, ojDeferred)
        println("coffee is ready")
        println("eggs are ready")
        println("bacon is ready")
        println("orange juice is ready")
        println("toast is ready")
        println("Breakfast is ready!")
    }

    private suspend fun pourCoffeeAsync(scope: CoroutineScope) =
        scope.async {
            delay()
            Coffee()
        }


    private suspend fun fryEggsAsync(scope: CoroutineScope, amount: Int) =
        scope.async {
            delay(amount)
            Egg()
        }

    private suspend fun fryBaconAsync(scope: CoroutineScope, slices: Int) =
        scope.async {
            delay(slices)
            Bacon()
        }

    private suspend fun toastBreadAsync(scope: CoroutineScope, slices: Int) =
        scope.async {
            delay(slices)
            val plainToast = Toast()
            applyButter(plainToast)
            applyJam(plainToast)
            plainToast
        }

    private suspend fun applyButter(toast: Toast) {
        delay()
    }

    private suspend fun applyJam(toast: Toast) {
        delay()
    }

    private suspend fun pourOrangeJuiceAsync(scope: CoroutineScope) =
        scope.async {
            delay()
            Juice()
        }
}