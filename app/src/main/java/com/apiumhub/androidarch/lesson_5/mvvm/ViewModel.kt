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
import com.apiumhub.androidarch.lesson_4.domain.entity.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(
    private val getUsers: GetUsers =
        GetUsers(
            UsersRetrofitRepository(UsersApi.create()),
            UsersRoomRepository(AppDb.getDb().userDao())
        ),
    dispatcher: CoroutineDispatcher = Dispatchers.Main

) : ViewModel() {

    private val usersInternal = MutableLiveData<ViewModelState>()
    val users: LiveData<ViewModelState> = usersInternal

    init {
        viewModelScope.launch(dispatcher) {
            usersInternal.postValue(ViewModelState.Loading)
            getUsers.execute().fold({
                usersInternal.postValue(ViewModelState.Error(it))
            },{
                usersInternal.postValue(ViewModelState.Success(it))
            })
        }
    }

    fun reload() {

    }
}

sealed class ViewModelState {
    object Loading: ViewModelState()
    data class Error(val error: Throwable): ViewModelState()
    data class Success(val users: List<User>): ViewModelState()
}