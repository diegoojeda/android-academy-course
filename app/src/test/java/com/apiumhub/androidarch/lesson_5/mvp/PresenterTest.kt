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

    @Test
    fun `should show users list on UI when result is success`() = runBlocking {
        useCaseHappyPath()

        presenter.start()

        verify {
            ui.onDataLoaded(usersList)
        }
    }

    @Test
    fun `should show no connection error when there's no connection`() = runBlocking {
        coEvery {
            getUsers.execute()
        } returns Try.raiseError(NoInternetConnectionException())

        presenter.start()

        verify { ui.showNoConnectionError() }
    }

    @Test
    fun `should show generic error when exception is unknown`() = runBlocking {
        coEvery {
            getUsers.execute()
        } returns Try.raiseError(Error())

        presenter.start()

        verify { ui.showGenericError() }
    }

    @Test
    fun `should show loading when the presenter starts`() = runBlocking {
        useCaseHappyPath()
        presenter.start()
        verify {
            ui.showLoading()
        }
    }

    @Test
    fun `should hide loading when the use case executes`() = runBlocking {
        useCaseHappyPath()
        presenter.start()
        verify {
            ui.hideLoading()
        }
    }

    @Test
    fun `should hide error when use case is successful`() = runBlocking {
        useCaseHappyPath()
        presenter.start()
        verify {
            ui.hideError()
        }
    }

    private fun useCaseHappyPath() {
        coEvery {
            getUsers.execute()
        } returns Try.just(usersList)
    }
}