package com.apiumhub.androidarch.lesson_5.mvp

import com.apiumhub.androidarch.lesson_4.domain.GetUsers
import io.mockk.mockk
import org.junit.Before

class PresenterTest {

    private val ui = mockk<Presenter.Contract>(relaxed = true)
    private val getUsers = mockk<GetUsers>()
    private lateinit var presenter: Presenter

    @Before
    fun setUp() {
        presenter = Presenter(ui, getUsers)
    }
}