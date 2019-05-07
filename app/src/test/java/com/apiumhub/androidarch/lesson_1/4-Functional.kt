package com.apiumhub.androidarch.lesson_1

import arrow.core.Either
import arrow.core.Option
import arrow.core.curry
import arrow.core.getOrElse
import arrow.syntax.function.curried
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class `4-Functional` {

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

    //region 2 - Currying

    @Test
    fun currying() {
        val toCurry: (String, Int) -> String = { p1: String, p2: Int -> "p1 is: $p1 and p2 is: $p2" }
        val curried: (String) -> (Int) -> String = toCurry.curry()//From arrow-core, along with compose and andThen
        println(toCurry("foo", 1))
        println(curried("bar")(5))
    }

    @Test
    fun curryingNParameters() {
        val toCurry: (String, String, String, String, String, String) -> String =
            { p1, p2, p3, p4, p5, p6 -> "p1: $p1, p2: $p2, p3: $p3, p4: $p4, p5: $p5, p6: $p6" }
        val curried = toCurry.curried()
        println(toCurry("Lorem", "ipsum", "dolor", "sit", "amet", "consectetur"))
        println(curried("Lorem")("ipsum")("dolor")("sit")("amet")("consectetur"))
    }

    @Test
    fun curryingSumAndMultiply() {
        val sumAndMultiply: (Int, Int, Int) -> Int = { p1, p2, p3 -> p1 + p2 * p3 }
        println("Original: ${sumAndMultiply(1, 2, 3)}")
        val curried = sumAndMultiply.curried()
        val product = curried(1).invoke(2)
        val applied3 = product(3)
        val result = applied3
        println("Result: $result")
    }
    //endregion

    //region 3 - Operators

    @Test
    fun map() {
        val list = listOf(1, 2, 3)
        println(list)
        val squared = list.map { it * it }
        println(squared)
    }

    @Test
    fun fold() {
        val sum = listOf(1, 2, 3).fold(0) { acc, next -> acc + next }
        println(sum)
        val sumReduced = listOf(1, 2, 3).reduce { acc, next -> acc + next }
        println(sumReduced)
//        val emptyListReduced = emptyList<Int>().reduce { acc, next -> acc + next } //Crashes as an empty list can't be reduced
    }

    @Test
    fun flatMap() {
        val listOfLists = listOf(listOf(1), listOf(2), listOf(3), listOf(4))
        val mapped = listOfLists.map { lists -> listSquared(lists) }
        val flatMapped = listOfLists.flatMap { lists -> listSquared(lists) }
        println("Mapped: $mapped")
        println("Flattened: ${mapped.flatten()}")
        println("FlatMapped: $flatMapped")
    }

    private fun listSquared(lists: List<Int>) =
        listOf(lists.first(), lists.first() * lists.first())

    //endregion

    //region 4 - Monads

    @Test
    fun either() {
        val left = Either.left(Error())
        val right = Either.right("value")
        println("Fold")
        eitherFold(left, right)
        println("Map")
        eitherMap(left, right)
        println("Extracting values from an Either is usually done via fold, where you get access to both possible values.")
    }

    private fun eitherFold(left: Either<Error, String>, right: Either<Error, String>) {
        left.fold({
            println("Value is left")
        }, {
            println("Value is right")
        })
        right.fold({
            println("Value is left")
        }, {
            println("Value is right")
        })
    }

    private fun eitherMap(left: Either<Error, String>, right: Either<Nothing, String>) {
        //Map only applies to Either.right, so if there's a value on left, it won't be mapped
        val leftMap = left.map { "new value" }
        val rightMap = right.map { "new value" }
        println(leftMap)
        println(rightMap)
        //There's a mapLeft method that maps the value on the left
        val leftMapLeft = left.mapLeft { Error("New error") }
        println(leftMapLeft)
    }

    @Test
    fun option() {
        val some = Option.just("value")
        val none = Option.empty<String>()
        println("Fold")
        optionFold(some, none)
        println("Map")
        optionMap(some, none)
        println("Extracting values")
        extractOption(some, none)
    }

    private fun optionFold(some: Option<String>, none: Option<String>) {
        some.fold({
            println("This is never printed.")
        }, {
            println("Value is present and is: $it")
        })
        none.fold({
            println("This optional is None")
        }, {
            println("This is never printed.")
        })
    }

    private fun optionMap(some: Option<String>, none: Option<String>) {
        println(some.map { "a new value" })
        println(none.map { "This won't be printed" })
    }

    private fun extractOption(some: Option<String>, none: Option<String>) {
        val someExtracted = some.getOrElse { "value not present" }
        val noneExtracted = none.getOrElse { "value not present" }
        println(someExtracted)
        println(noneExtracted)
    }

    //endregion
}