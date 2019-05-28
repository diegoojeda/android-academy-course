package com.apiumhub.androidarch.lesson_8

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apiumhub.androidarch.lesson_4.domain.GetUsers
import com.apiumhub.androidarch.lesson_6.mvvm.ViewModelEvent
import kotlinx.coroutines.launch

class KoinViewModel(private val useCase: GetUsers): ViewModel() {
    private val usersMutable = MutableLiveData<ViewModelEvent>()

    val users: LiveData<ViewModelEvent> = usersMutable

    init {
        viewModelScope.launch {
            getData()
        }
    }

    private suspend fun getData() {
        usersMutable.postValue(ViewModelEvent.Loading)
        useCase.execute().fold({
            usersMutable.postValue(ViewModelEvent.Error)
        }, {
            usersMutable.postValue(ViewModelEvent.Success(it))
        })
    }

    fun retry() {
        viewModelScope.launch {
            getData()
        }
    }
}