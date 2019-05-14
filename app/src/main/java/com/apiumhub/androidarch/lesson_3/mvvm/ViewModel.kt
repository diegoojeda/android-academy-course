package com.apiumhub.androidarch.lesson_3.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apiumhub.androidarch.lesson_3.common.ConnectionProvider
import com.apiumhub.androidarch.lesson_3.common.DatabaseClient
import com.apiumhub.androidarch.lesson_3.common.NetworkClient

class ViewModel : ViewModel() {
    private val networkClient = NetworkClient()
    private val databaseClient = DatabaseClient()
    private val connectionProvider = ConnectionProvider()

    private val mutableUsers = MutableLiveData<ViewModelEvent>()
    val users: LiveData<ViewModelEvent> = mutableUsers

}