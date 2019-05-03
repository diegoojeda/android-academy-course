package com.apiumhub.androidarch.lesson_1

import org.junit.Test

class `2-Nullability` {

    //region1 - Nullability with '?'
    @Test
    fun typicalNullability() {
        var a = "test"
//        a = null //Compile time error
        var b: String? = "test"
        b = null
//        println(b.length) //Compile time error, missing `?`
        println(b?.length) //Compiles, but prints null
    }
    //endregion

}