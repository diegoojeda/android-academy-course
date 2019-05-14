package com.apiumhub.androidarch.lesson_3.mvp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apiumhub.androidarch.R
import com.apiumhub.androidarch.lesson_3.common.*
import kotlinx.android.synthetic.main.home_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class PresenterActivity: AppCompatActivity(), PresenterSolution.Contract, CoroutineScope by MainScope() {
    private val presenter = PresenterSolution(
        this,
        NetworkClient(),
        DatabaseClient(),
        ConnectionProvider()
    )

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