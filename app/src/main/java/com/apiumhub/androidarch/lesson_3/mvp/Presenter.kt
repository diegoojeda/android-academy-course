package com.apiumhub.androidarch.lesson_3.mvp

import com.apiumhub.androidarch.lesson_3.common.DatabaseClient
import com.apiumhub.androidarch.lesson_3.common.NetworkClient
import com.apiumhub.androidarch.lesson_3.common.User

class Presenter(
    private val networkClient: NetworkClient,
    private val databaseClient: DatabaseClient
) {
    interface Contract {
        fun onDataLoaded(users: List<User>)
        fun onNoNetworkConnectionError()
        fun onError()
    }

    fun start() {
        //TODO
    }

}