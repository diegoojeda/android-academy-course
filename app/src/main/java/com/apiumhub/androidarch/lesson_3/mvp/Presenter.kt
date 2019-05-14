package com.apiumhub.androidarch.lesson_3.mvp

import com.apiumhub.androidarch.lesson_3.common.ConnectionProvider
import com.apiumhub.androidarch.lesson_3.common.DatabaseClient
import com.apiumhub.androidarch.lesson_3.common.NetworkClient
import com.apiumhub.androidarch.lesson_3.common.User

class Presenter(
    private var ui: Contract?,
    private val networkClient: NetworkClient,
    private val databaseClient: DatabaseClient,
    private val connectionProvider: ConnectionProvider
) {
    interface Contract {
        fun onDataLoaded(users: List<User>)
        fun hideError()
        fun onError()
        fun showLoading()
        fun hideLoading()
    }


    fun start() {
        //TODO
    }

}