package com.apiumhub.androidarch.lesson_5.mvp

import arrow.core.Try
import com.apiumhub.androidarch.lesson_4.domain.GetUsers
import com.apiumhub.androidarch.lesson_4.domain.entity.User
import com.apiumhub.androidarch.lesson_4.domain.exception.NoInternetConnectionException
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PresenterTest {

    private val ui = mockk<Presenter.Contract>(relaxed = true)
    private val getUsers = mockk<GetUsers>()
    private lateinit var presenter: Presenter

    private val usersList = listOf<User>()

    @Before
    fun setUp() {
        presenter = Presenter(ui, getUsers)
    }


}