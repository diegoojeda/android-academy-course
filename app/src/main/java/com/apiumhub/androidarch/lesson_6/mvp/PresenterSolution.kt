package com.apiumhub.androidarch.lesson_6.mvp

import com.apiumhub.androidarch.lesson_6.common.*

class PresenterSolution(
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

    suspend fun start() {
        ui?.showLoading()
        databaseClient.getUsers().fold({ loadFromNetworkClient() }, ::showData)
    }

    private suspend fun loadFromNetworkClient() {
        if (connectionProvider.hasNetworkConnection()) {
            networkClient.getUsers().fold(::showError) {
                showData(it)
                databaseClient.storeUsers(it)
            }
        } else {
            showError(NoNetworkConnectionError())
        }
    }

    private fun showError(error: Error) {
        //Can do pattern matching on the error
        ui?.hideLoading()
        ui?.onError()
    }

    private fun showData(users: List<User>) {
        ui?.hideLoading()
        ui?.hideError()
        ui?.onDataLoaded(users)
    }

    fun stop() {
        ui = null
    }
}

