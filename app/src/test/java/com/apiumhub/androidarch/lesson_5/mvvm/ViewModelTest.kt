package com.apiumhub.androidarch.lesson_5.mvvm

import arrow.core.Try
import com.apiumhub.androidarch.lesson_4.domain.GetUsers
import com.apiumhub.androidarch.lesson_4.domain.exception.NoInternetConnectionException
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test
import java.util.concurrent.CountDownLatch

class ViewModelTest {

    private val getUsers = mockk<GetUsers>()

    private lateinit var viewModel: ViewModel

    @Before
    fun setUp() {
        viewModel = ViewModel(getUsers, Dispatchers.Unconfined)
    }

    @Test(timeout = 1000)
    fun `should emit a no connection error when there is no connection`()  {
        val countDownLatch = CountDownLatch(1)
        coEvery {
            getUsers.execute()
        } returns Try.raiseError(NoInternetConnectionException())

        viewModel.users.observeForever {
            assertTrue(it is ViewModelEvent.Error)
            countDownLatch.countDown()
        }
        countDownLatch.await()
    }
}