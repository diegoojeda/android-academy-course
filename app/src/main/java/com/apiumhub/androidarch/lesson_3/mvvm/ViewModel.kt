package com.apiumhub.androidarch.lesson_3.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apiumhub.androidarch.lesson_3.common.DatabaseClient
import com.apiumhub.androidarch.lesson_3.common.NetworkClient
import com.apiumhub.androidarch.lesson_3.common.User

class ViewModel : ViewModel() {

    private val networkClient = NetworkClient()
    private val databaseClient = DatabaseClient()

    val users = MutableLiveData<List<User>>()

    fun start() {

    }

}