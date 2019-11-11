package com.apiumhub.androidarch.lesson_5.mvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Try
import com.apiumhub.androidarch.lesson_4.domain.GetUsers
import com.apiumhub.androidarch.lesson_4.domain.entity.User
import com.apiumhub.androidarch.lesson_5.common.NoNetworkConnectionError
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import java.util.concurrent.CountDownLatch

class ViewModelTest {

    private val getUsers = mockk<GetUsers>()

    private lateinit var viewModel: ViewModel

    private val myUsersList = listOf(User(UUID.randomUUID().toString(), "alias", "avatar"))
    private val usersTry = Try.just(myUsersList)

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = ViewModel(getUsers, Dispatchers.Unconfined)
    }

    @Test
    fun `should emit users through live data returned by the service`() = runBlocking {
        coEvery {
            getUsers.execute()
        } returns usersTry

        viewModel.getData()
        viewModel.users.observeForever {
            assert(it is ViewModelState.Success)
            assertEquals(myUsersList, (it as ViewModelState.Success).users)
        }
    }

    @Test(timeout = 1000)
    fun `should emit error through live data when service fails`() = runBlocking {
        val countDownLatch = CountDownLatch(1)
        coEvery {
            getUsers.execute()
        } returns Try.raiseError(NoNetworkConnectionError())

        viewModel.getData()
        viewModel.users.observeForever {
            assert(it is ViewModelState.Error)
            assert((it as ViewModelState.Error).error is NoNetworkConnectionError)
            countDownLatch.countDown()
        }
        countDownLatch.await()
    }
}