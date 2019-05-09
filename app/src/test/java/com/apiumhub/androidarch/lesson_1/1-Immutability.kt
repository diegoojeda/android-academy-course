package com.apiumhub.androidarch.lesson_1

import org.junit.Test

class `1-Immutability` {

    //region 1 - Mutable collections are dangerous
    @Test
    fun mutableList() {
        //When adding a value to a mutable list, we don't know how many references can this list hold -> DANGEROUS!!
        println("1 - mutableList")
        val mutableList = mutableListOf(1, 2, 3)
        println("Mutable list before adding: $mutableList")
        val otherList: List<Int> = mutableList
        mutableList.add(4)
        println("Other list after adding: $otherList")
    }
    //endregion

    //region 2 - Immutable collections are never modified
    @Test
    fun immutableList() {
        println("2 - immutableList")
        val immutableList = listOf(1, 2, 3)
        println("Immutable before adding: $immutableList")
        val otherList = immutableList.toMutableList().apply { add(4) }.toList()
        println("Immutable after adding: $immutableList")
        println("Mutable after adding: $otherList")
    }
    //endregion

    //region 3 - How to "add" data to an immutable collection then?
    @Test
    fun addToImmutable() {
        println("3 - add to immutable")
        val immutableList = listOf(1, 2, 3)
        val newList = immutableList.plus(4)
        println("Original list is not modified: $immutableList")
        println("Data is added to a totally new list: $newList")
    }
    //endregion

//    //region 4 - How does this work with operators over collections?
//    @Test
//    fun filter() {
//        //Operators such as filter, map, flatmap... return a brand new list with the result of the operation; it does NOT modify the original list even if it's mutable
//        println("4 - filter")
//        val mutableList = mutableListOf(1, 2, 3)
//        val filteredList = mutableList.filter { it != 3 }
//        println("Initial mutable list: $mutableList")
//        println("Filtered list: $filteredList")
//    }
//    //endregion
}