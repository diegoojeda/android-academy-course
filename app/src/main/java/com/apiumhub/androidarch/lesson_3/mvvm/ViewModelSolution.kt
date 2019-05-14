package com.apiumhub.androidarch.lesson_3.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apiumhub.androidarch.lesson_3.common.*

class ViewModelSolution : ViewModel() {
    private val networkClient = NetworkClient()
    private val databaseClient = DatabaseClient()
    private val connectionProvider = ConnectionProvider()

    private val mutableUsers = MutableLiveData<ViewModelEvent>()
    val users: LiveData<ViewModelEvent> = mutableUsers

    suspend fun start() {
        databaseClient.getUsers().fold({
            loadFromNetworkClient()
        }, {
            mutableUsers.postValue(ViewModelEvent.Success(it))
        })
    }

    private suspend fun loadFromNetworkClient() {
        if (connectionProvider.hasNetworkConnection()) {
            networkClient.getUsers().fold(::postError) {
                mutableUsers.postValue(ViewModelEvent.Success(it))
                databaseClient.storeUsers(it)
            }
        } else {
            postError(NoNetworkConnectionError())
        }
    }

    private fun postError(error: Error) {
        mutableUsers.postValue(ViewModelEvent.Error)
    }
}

sealed class ViewModelEvent {
    object Loading : ViewModelEvent()
    data class Success(val data: List<User>) : ViewModelEvent()
    object Error : ViewModelEvent()
}

