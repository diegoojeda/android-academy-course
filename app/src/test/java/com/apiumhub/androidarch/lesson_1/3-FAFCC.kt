package com.apiumhub.androidarch.lesson_1

import org.junit.Test

class `3-FAFCC` {

    @Test
    fun firstClassCitizens() {
        //We can store references to functions to be used later
        val functionReference = ::myFunction
        println(functionReference(1))

        //We can also define functions that accept other functions as parameters.
        //This is a function that takes two parameters, the first one is a function that receives an Int and returns a String,
        //the second one is an Int
        val functionWithFunctionParameters: ((Int) -> String, Int) -> String = { p1: (Int) -> String, p2: Int ->
            p1(p2)
        }
        //So, we can call this function passing the previous one (functionReference) as the first parameter
        println(functionWithFunctionParameters(functionReference, 1))

        //A function can also return other functions
        println(integerToString().invoke(1))
    }

    fun myFunction(p1: Int): String = p1.toString()
    fun integerToString(): (Int)-> String = ::myFunction
}