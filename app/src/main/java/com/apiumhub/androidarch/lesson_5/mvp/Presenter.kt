package com.apiumhub.androidarch.lesson_5.mvp

import com.apiumhub.androidarch.lesson_4.domain.GetUsers
import com.apiumhub.androidarch.lesson_4.domain.entity.User
import com.apiumhub.androidarch.lesson_4.domain.exception.NoInternetConnectionException

class Presenter(
    private var ui: Contract?,
    private val getUsers: GetUsers
) {
    interface Contract {
        fun onDataLoaded(users: List<User>)
        fun hideError()
        fun showGenericError()
        fun showLoading()
        fun hideLoading()
        fun showNoConnectionError()
    }

    suspend fun start() {

    }

    fun stop() {
        ui = null
    }

}