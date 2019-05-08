package com.apiumhub.androidarch.lesson_2.breakfast

import org.junit.Test

class `1-BreakfastSequentially` {
    /*
    Blocking execution is like starting to pour coffee and not being able to even speak to someone while it's pouring.
    Sequentially preparing breakfast takes almost 20 seconds
     */
    @Test
    fun prepareBreakfastSequentially() {
        pourCoffee()
        println("coffee is ready")
        fryEggs(2)
        println("eggs are ready")
        fryBacon(3)
        println("bacon is ready")
        val toast = toastBread(2)
        applyButter(toast)
        applyJam(toast)
        println("toast is ready")
        pourOrangeJuice()
        println("orange juice is ready")
        println("Breakfast is ready!")
    }

    private fun pourCoffee(): Coffee {
        sleep()
        return Coffee()
    }

    private fun fryEggs(amount: Int): Egg {
        sleep(amount)
        return Egg()
    }

    private fun fryBacon(slices: Int): Bacon {
        sleep(slices)
        return Bacon()
    }

    private fun toastBread(slices: Int): Toast {
        sleep(slices)
        return Toast()
    }

    private fun applyButter(toast: Toast) {
        sleep()
    }

    private fun applyJam(toast: Toast) {
        sleep()
    }

    private fun pourOrangeJuice(): Juice {
        sleep()
        return Juice()
    }

}