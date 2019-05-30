package com.apiumhub.androidarch.lesson_5.mvp

import com.apiumhub.androidarch.lesson_4.domain.GetUsers
import com.apiumhub.androidarch.lesson_4.domain.entity.User
import com.apiumhub.androidarch.lesson_4.domain.exception.NoInternetConnectionException

class PresenterSolution(
    private var ui: Presenter.Contract?,
    private val getUsers: GetUsers
) {
    suspend fun start() {
        ui?.showLoading()
        getUsers.execute().fold(::showError, ::showData)
    }

    private fun showError(error: Throwable) {
        when (error) {
            is NoInternetConnectionException -> ui?.showNoConnectionError()
            else -> ui?.onError()
        }
        ui?.hideLoading()
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

