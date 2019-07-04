package com.apiumhub.androidarch.lesson_2.breakfast

import org.junit.Test

class `2-BreakfastCallbacks` {
    private lateinit var coffee: Coffee
    private lateinit var eggs: Egg
    private lateinit var bacon: Bacon
    private lateinit var toast: ToastWithJamAndButter
    private lateinit var juice: Juice

    /*
    When using callbacks, we need to manage data synchronization ourselves. In this example we need to hold a reference
    to each element, and check periodically if it's all finished. We could also move the check inside every callback,
    meaning that we'd duplicate this check on every item that we wanted to add to our breakfast
     */
    /*
     Esto se puede complicar hasta el infinito si queremos, añadiendo recursos compartidos (como la sartén),
     manejo de estos recursos con semáforos, deadlocks...
     */

    @Test
    fun breakfastWithCallbacks() {
        pourCoffee {
            println("coffee is ready")
            coffee = it
        }
        fryEggs(2) {
            println("eggs are ready")
            eggs = it
        }
        fryBacon(3) {
            println("bacon is ready")
            bacon = it
        }
        toastBread(2) { plainToast ->
            applyButter(plainToast) { toastWithButter ->
                applyJam(toastWithButter) { toastWithJam ->
                    toast = toastWithJam
                }
            }
            println("toast is ready")
        }
        pourOrangeJuice {
            println("orange juice is ready")
            juice = it
        }
        waitForBreakfastReady()
    }

    //region common
    private fun pourCoffee(func: (coffee: Coffee) -> Unit) {
        Thread {
            sleep()
            func(Coffee())
        }.start()
    }

    private fun fryEggs(amount: Int, func: (eggs: Egg) -> Unit) {
        Thread {
            sleep(amount)
            func(Egg())
        }.start()
    }

    private fun fryBacon(slices: Int, func: (bacon: Bacon) -> Unit) {
        Thread {
            sleep(slices)
            func(Bacon())
        }.start()
    }

    private fun toastBread(slices: Int, func: (toast: Toast) -> Unit) {
        Thread {
            sleep(slices)
            func(Toast())
        }.start()
    }

    private fun applyButter(toast: Toast, func: (ToastWithButter) -> Unit) {
        sleep()
        func(ToastWithButter())
    }

    private fun applyJam(toast: ToastWithButter, func: (ToastWithJamAndButter) -> Unit) {
        sleep()
        func(ToastWithJamAndButter())
    }

    private fun pourOrangeJuice(func: (juice: Juice) -> Unit) {
        Thread {
            sleep()
            func(Juice())
        }.start()
    }

    private fun waitForBreakfastReady() {
        while (!::coffee.isInitialized || !::eggs.isInitialized || !::bacon.isInitialized || !::toast.isInitialized || !::juice.isInitialized) {
            Thread.sleep(1000)
        }
        println("Breakfast is ready!")
    }
    //endregion
}