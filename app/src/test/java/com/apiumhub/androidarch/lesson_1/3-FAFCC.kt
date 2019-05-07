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
        val functionWithFunctionAsParameter = ::functionWithFunctionAsParameter
        //So, we can call this function passing the previous one (functionReference) as the first parameter
        println(functionWithFunctionAsParameter(functionReference, 1))

        //A function can also return other functions
        println(integerToString()(1))
    }

    fun myFunction(p1: Int): String = p1.toString()

    fun functionWithFunctionAsParameter(func: (Int) -> String, p2: Int): String {
        return func(p2)
    }

    fun integerToString(): (Int) -> String = ::myFunction
}