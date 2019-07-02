package com.apiumhub.androidarch.lesson_1

import junit.framework.TestCase.assertEquals
import org.junit.Test

class `2-Nullability` {

    //region1 - Nullability with '?'
    @Test
    fun typicalNullability() {
        var a: String = "test"
//        a = null //Compile time error
        var b: String? = "test"
        b = null
//        println(b.length) //Compile time error, missing `?`
        println(b?.length) //Compiles, but prints null
//        println(b!!.length) //DANGEROUS -> Unsafe access. Crashes if b is null
    }
    //endregion

    @Test
    fun elvisAndLet() {
        var a: String? = null
        a?.let {
            assertEquals("Hello world", it)
        } ?: "Goodbye world"
    }
}