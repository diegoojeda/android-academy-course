package com.apiumhub.androidarch.lesson_1

import org.junit.Test

class `3-FAFCC` {

    @Test
    fun firstClassCitizens() {
        //We can store references to functions to be used later
        val intToString = ::intToStringFunction
        //And they can be invoked as any other function
        println(intToString(1))

        //We can also define functions that accept other functions as parameters.
        //This is a function that takes two parameters, the first one is an integer,
        // the second one is a  function that receives an Int and returns a String.
        //So, we can call this function passing the previous one (intToStringFunction) as the second parameter
        printIntAsString(1, intToString)

        //Lastly, a function can also return other functions
        println(getIntToStringFunction()(1))
        //This is useful, for example, in functions that receive functions as parameters, as the previous example.
        //It can be called also like this:
        printIntAsString(1, getIntToStringFunction())
        printIntAsString(1, getIntToStringFunction2())
        printIntAsString(1, getIntToStringFunction3())
    }

    //Regular function that takes an integer, and returns it as a string
    private fun intToStringFunction(value: Int): String = value.toString()

    //Function that takes an integer and a transformation to turn this integer into a String,
    //and prints the result of transforming the provided integer (value) into a String
    private fun printIntAsString(value: Int, transformation: (Int) -> String): String {
        return transformation(value)
    }

    //Function that returns a function that takes an integer as input parameter and returns a String
    //In this case it returns a reference to "intToStringFunction"
    private fun getIntToStringFunction() = ::intToStringFunction

    //Another way of writing functions that return functions is as:
    private fun getIntToStringFunction2() = { value: Int ->
        value.toString()
    }

    //One more way, specifying return type explicitly
    private fun getIntToStringFunction3(): (Int) -> String {
        return { value: Int -> value.toString() }
    }
}