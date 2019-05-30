package com.apiumhub.androidarch.lesson_5.mvp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apiumhub.androidarch.AppDb
import com.apiumhub.androidarch.R
import com.apiumhub.androidarch.lesson_4.data.db.UserDbEntity
import com.apiumhub.androidarch.lesson_4.data.db.UsersRoomRepository
import com.apiumhub.androidarch.lesson_4.data.db.toDomain
import com.apiumhub.androidarch.lesson_4.data.network.UsersApi
import com.apiumhub.androidarch.lesson_4.data.network.UsersRetrofitRepository
import com.apiumhub.androidarch.lesson_4.data.network.dto.UserNetworkDto
import com.apiumhub.androidarch.lesson_4.data.network.toDomain
import com.apiumhub.androidarch.lesson_4.domain.GetUsers
import com.apiumhub.androidarch.lesson_4.domain.entity.User
import com.apiumhub.androidarch.lesson_5.common.MainAdapter
import kotlinx.android.synthetic.main.home_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class PresenterActivity : AppCompatActivity(), Presenter.Contract, CoroutineScope by MainScope() {
    private val presenter by lazy {
        Presenter(
            this,
            GetUsers(
                UsersRetrofitRepository(UsersApi.create(), UserNetworkDto::toDomain),
                UsersRoomRepository(AppDb.getDb().userDao(), UserDbEntity::toDomain)
            )
        )
    }

    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)
        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRetryBtn.setOnClickListener { start() }
        start()
    }

    private fun start() {
        launch {
            presenter.start()
        }
    }

    override fun onDataLoaded(users: List<User>) {
        adapter.update(users)
    }

    override fun onError() {
        mainErrorTv.visibility = View.VISIBLE
        mainErrorTv.text = "Oops, something went wrong! \\n Please try again later!"
    }

    override fun showNoConnectionError() {
        mainErrorTv.visibility = View.VISIBLE
        mainErrorTv.text = "No internet connection \\n Please try again later!"
    }

    override fun showLoading() {
        mainLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        mainLoading.visibility = View.GONE
    }

    override fun hideError() {
        mainErrorTv.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
    }
}