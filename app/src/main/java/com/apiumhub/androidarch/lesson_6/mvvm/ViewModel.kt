package com.apiumhub.androidarch.lesson_6.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apiumhub.androidarch.lesson_6.common.ConnectionProvider
import com.apiumhub.androidarch.lesson_6.common.DatabaseClient
import com.apiumhub.androidarch.lesson_6.common.NetworkClient
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    private val networkClient = NetworkClient()
    private val databaseClient = DatabaseClient()
    private val connectionProvider = ConnectionProvider()

    private val usersMutable = MutableLiveData<ViewModelEvent>()

    val users: LiveData<ViewModelEvent> = usersMutable

    init {
        viewModelScope.launch {
            getData()
        }
    }

    private fun getData() {
        //TODO
    }

    fun retry() {
        viewModelScope.launch {
            getData()
        }
    }

}