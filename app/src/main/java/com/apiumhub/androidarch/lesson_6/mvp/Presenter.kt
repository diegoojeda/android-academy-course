package com.apiumhub.androidarch.lesson_6.mvp

import com.apiumhub.androidarch.lesson_6.common.ConnectionProvider
import com.apiumhub.androidarch.lesson_6.common.DatabaseClient
import com.apiumhub.androidarch.lesson_6.common.NetworkClient
import com.apiumhub.androidarch.lesson_6.common.User

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