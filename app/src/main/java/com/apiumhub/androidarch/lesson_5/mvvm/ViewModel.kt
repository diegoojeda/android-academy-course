package com.apiumhub.androidarch.lesson_5.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apiumhub.androidarch.AppDb
import com.apiumhub.androidarch.lesson_4.data.db.UsersRoomRepository
import com.apiumhub.androidarch.lesson_4.data.network.UsersApi
import com.apiumhub.androidarch.lesson_4.data.network.UsersRetrofitRepository
import com.apiumhub.androidarch.lesson_4.domain.GetUsers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(
    private val getUsers: GetUsers =
        GetUsers(
            UsersRetrofitRepository(UsersApi.create()),
            UsersRoomRepository(AppDb.getDb().userDao())
        ),
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main

) : ViewModel() {

    private val usersMutable = MutableLiveData<ViewModelEvent>()

    val users: LiveData<ViewModelEvent> = usersMutable

    init {
        viewModelScope.launch(dispatcher) {
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