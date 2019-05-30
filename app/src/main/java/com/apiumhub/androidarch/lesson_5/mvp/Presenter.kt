package com.apiumhub.androidarch.lesson_5.mvp

import com.apiumhub.androidarch.lesson_4.domain.GetUsers
import com.apiumhub.androidarch.lesson_4.domain.entity.User

class Presenter(
    private var ui: Contract?,
    private val getUsers: GetUsers
) {
    interface Contract {
        fun onDataLoaded(users: List<User>)
        fun hideError()
        fun onError()
        fun showLoading()
        fun hideLoading()
        fun showNoConnectionError()
    }

    fun start() {
        //TODO
    }

    fun stop() {
        ui = null
    }

}