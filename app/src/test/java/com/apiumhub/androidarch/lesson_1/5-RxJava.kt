package com.apiumhub.androidarch.lesson_1

import io.reactivex.Observable
import org.junit.Test

class `5-RxJava` {

    //region 1 - RxJava is functional & declarative!
    @Test
    fun RxJava() {
        val observable = Observable
                .just(1, 2, 3, 4, 5)
                .map(::square)
                .filter(::equalsNine)
                .doOnNext { printEmit(it) }
                .doOnComplete(::printComplete)

        observable.subscribe {
            println("Received value $it")
        }
    }

    private fun square(value: Int): Int = value * value

    private fun equalsNine(value: Int): Boolean = value != 9

    private fun printEmit(value: Int) {
        println("This is a side effect when observable emits a value. Value emitted: $value")
    }

    private fun printComplete() {
        println("This is a side effect when observable completes")
    }

    //endregion

}