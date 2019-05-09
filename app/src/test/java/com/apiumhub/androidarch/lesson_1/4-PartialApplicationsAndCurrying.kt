package com.apiumhub.androidarch.lesson_1

import arrow.syntax.function.curried
import arrow.syntax.function.partially1
import arrow.syntax.function.partially2
import arrow.syntax.function.partially3
import org.junit.Assert
import org.junit.Test

class `4-PartialApplicationsAndCurrying` {

    /*
    Partial applications let us "fix" certain parameters to a function.
     */


    //region 1-addOne
    @Test
    fun addOne() {
        val addOne = ::sum.partially1(1)
        Assert.assertEquals(3, addOne(2))
    }

    private fun sum(a: Int, b: Int): Int {
        return a + b
    }

    //endregion

    //region 2-join with commas
    @Test
    fun joinWithCommas() {
        val joinWithComma = ::joinStringList.partially2(",")
        val result = joinWithComma(listOf("This", "will", "be", "separated", "with", "a", "comma"))
        println(result)
        Assert.assertEquals("This,will,be,separated,with,a,comma", result)
    }

    private fun joinStringList(list: List<String>, character: String): String {
        return list.joinToString(separator = character)
    }
    //endregion

    //region 3-Apply more than one parameter

    @Test
    fun applyManyParameters() {
        val prefixAndBang = ::formatString.partially3("!")
        val greetAndBang = prefixAndBang.partially1("Hello, ")
        println(greetAndBang("world"))
        println(greetAndBang("partial applications"))
        println(greetAndBang("functional programming"))
    }

    private fun formatString(prefix: String, infix: String, postfix: String) = "$prefix$infix$postfix"
    //endregion

    //region 4-Currying

    @Test
    fun curryingSum() {
        val curried = ::sumToCurry.curried()
        Assert.assertEquals(sumToCurry(2, 3), curried(2)(3))
    }

    private fun sumToCurry(a: Int, b: Int) = a + b

    //endregion

    //region 5-How is this even useful?

    //Currying and partial applications are really useful when you want to call a function
    //but you don't know the value for all of the parameters. This is a common case when, for example,
    //you have a flow consisting of a few steps where you get information at different steps.
    //Let's see an example

    @Test
    fun utility() {
        val stepOne = Step1()
        val stepTwo = Step2()
        val userNameAndPasswordApplied = stepOne.applyUserNameAndPaasword(::registerUser)
        val privacyPolicyApplied = stepTwo.applyUserAcceptsPrivacyPolicy(userNameAndPasswordApplied)
        //the function "registerUser" won't run until we execute
        //the "privacyPolicyApplied" function
        privacyPolicyApplied()
    }

    private fun registerUser(userName: String, password: String, userAcceptsPrivacyPolicy: Boolean) {
        println("Registering user")
        //registerService.registerUser(userName, password, userAcceptsPrivacyPolicy)
    }

    class Step1 {
        fun applyUserNameAndPaasword(func: (String, String, Boolean) -> Unit): (Boolean) -> Unit {
            println("Partially applying username and password")
            return func.partially1("userName").partially1("password")
        }
    }

    class Step2 {
        fun applyUserAcceptsPrivacyPolicy(func: (Boolean) -> Unit): () -> Unit {
            println("Partially applying privacy policy")
            return func.partially1(true)
        }
    }

    //endregion
}