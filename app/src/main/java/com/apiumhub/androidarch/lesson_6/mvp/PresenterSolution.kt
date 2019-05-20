package com.apiumhub.androidarch.lesson_6.mvp

import com.apiumhub.androidarch.lesson_4.domain.GetUsers
import com.apiumhub.androidarch.lesson_4.domain.entity.User

class PresenterSolution(
    private var ui: Contract?,
    private val getUsers: GetUsers
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
        getUsers.execute().fold(::showError, ::showData)
    }

    private fun showError(error: Throwable) {
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

