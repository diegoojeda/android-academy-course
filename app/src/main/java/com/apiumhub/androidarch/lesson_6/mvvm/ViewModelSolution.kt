package com.apiumhub.androidarch.lesson_6.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apiumhub.androidarch.lesson_4.domain.entity.User
import com.apiumhub.androidarch.lesson_6.common.ConnectionProvider
import com.apiumhub.androidarch.lesson_6.common.DatabaseClient
import com.apiumhub.androidarch.lesson_6.common.NetworkClient
import kotlinx.coroutines.launch

class ViewModelSolution : ViewModel() {
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

    private suspend fun getData() {
        usersMutable.postValue(ViewModelEvent.Loading)
        databaseClient.getUsers().fold({
            loadFromNetworkClient()
        }, {
            usersMutable.postValue(ViewModelEvent.Success(it))
        })
    }

    private suspend fun loadFromNetworkClient() {
        if (connectionProvider.hasNetworkConnection()) {
            networkClient.getUsers().fold({
                usersMutable.postValue(ViewModelEvent.Error)
            }, {
                usersMutable.postValue(ViewModelEvent.Success(it))
                databaseClient.storeUsers(it)
            })
        } else {
            usersMutable.postValue(ViewModelEvent.Error)
        }
    }

    fun retry() {
        viewModelScope.launch {
            getData()
        }
    }
}

sealed class ViewModelEvent {
    object Loading : ViewModelEvent()
    data class Success(val data: List<User>) : ViewModelEvent()
    object Error : ViewModelEvent()
}

