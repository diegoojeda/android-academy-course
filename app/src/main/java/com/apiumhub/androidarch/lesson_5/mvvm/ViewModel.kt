package com.apiumhub.androidarch.lesson_5.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apiumhub.androidarch.AppDb
import com.apiumhub.androidarch.lesson_4.data.db.UserDbEntity
import com.apiumhub.androidarch.lesson_4.data.db.UsersRoomRepository
import com.apiumhub.androidarch.lesson_4.data.db.toDomain
import com.apiumhub.androidarch.lesson_4.data.network.UsersApi
import com.apiumhub.androidarch.lesson_4.data.network.UsersRetrofitRepository
import com.apiumhub.androidarch.lesson_4.data.network.dto.UserNetworkDto
import com.apiumhub.androidarch.lesson_4.data.network.toDomain
import com.apiumhub.androidarch.lesson_4.domain.GetUsers
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    private val getUsers = GetUsers(
        UsersRetrofitRepository(UsersApi.create(), UserNetworkDto::toDomain),
        UsersRoomRepository(AppDb.getDb().userDao(), UserDbEntity::toDomain)
    )

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